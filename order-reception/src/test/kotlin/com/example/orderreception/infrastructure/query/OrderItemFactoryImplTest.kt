package com.example.orderreception.infrastructure.query

import com.example.orderreception.domain.model.order.OrderItem
import com.example.orderreception.infrastructure.entity.generated.*
import com.example.orderreception.infrastructure.mapper.generated.*
import com.example.orderreception.presentation.order.AttributeParam
import com.example.orderreception.presentation.order.OrderItemParam
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import java.math.BigDecimal
import java.time.LocalDateTime

@SpringBootTest
class OrderItemFactoryImplTest(
    @Autowired private val sut: OrderItemFactoryImpl,
    @Autowired private val chainsBaseMapper: ChainsBaseMapper,
    @Autowired private val shopsBaseMapper: ShopsBaseMapper,
    @Autowired private val itemsBaseMapper: ItemsBaseMapper,
    @Autowired private val itemAttributesMappler: ItemAttributesBaseMapper,
    @Autowired private val attributesBaseMapper: AttributesBaseMapper
) {
    @Test
    @Sql(
        scripts = ["sql/clear.sql"],
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    fun 正常に商品のリストが取得できること() {
        // given
        insertTestData()

        val orderItemParams = listOf(
            OrderItemParam(
                itemId = 1,
                price = BigDecimal.valueOf(100),
                attributes = listOf(AttributeParam(attributeId = 1)),
                quantity = 1
            ),
            OrderItemParam(
                itemId = 2,
                price = BigDecimal.valueOf(200),
                attributes = listOf(
                    AttributeParam(attributeId = 2),
                    AttributeParam(attributeId = 3)
                ),
                quantity = 2
            ),
            OrderItemParam(
                itemId = 3,
                price = BigDecimal.valueOf(300),
                attributes = listOf(
                    AttributeParam(attributeId = 2),
                    AttributeParam(attributeId = 3),
                    AttributeParam(attributeId = 4),
                ),
                quantity = 3
            )
        )

        // when
        val results = sut.createOrderItems(orderItemParams, 1, 1).sortedBy { it.itemId }

        // then
        assert_パラメータで渡した商品の情報がDBから正常に取得できること(results, orderItemParams)
    }

    @Test
    fun 検索条件のチェーン情報と商品情報の整合性がない場合() {
        // given
        val orderItemParams = listOf(
            OrderItemParam(
                itemId = 1,
                price = BigDecimal.valueOf(100),
                attributes = listOf(AttributeParam(attributeId = 1)),
                quantity = 1
            ),
        )

        // when
        sut.createOrderItems(orderItemParams, 100, 1) // 商品情報と紐づいていないchainIdを検索条件に渡す
        // then

    }

    private fun insertTestData() {
        // テスト商品データ
        val itemsBaseList = (1..4).map { itemId ->
            ItemsBase(
                itemId = itemId.toLong(),
                chainId = 1,
                shopId = 1,
                name = "テスト商品${itemId}",
                price = itemId * 100L,
                description = "テスト商品${itemId}説明",
                createdAt = LocalDateTime.of(2000, 1, 2, 3, 4),
                updatedAt = LocalDateTime.of(2000, 1, 2, 3, 4),
            )
        }

        val attributesBaseList = (1..4).map { attributeId ->
            AttributesBase(
                attributeId = attributeId.toLong(),
                name = "属性名${attributeId}",
                value = "属性値${attributeId}",
                createdAt = LocalDateTime.of(2000, 1, 2, 3, 4),
                updatedAt = LocalDateTime.of(2000, 1, 2, 3, 4),
            )
        }

        val itemAttributePairs =
            listOf(Pair(1L, 1L), Pair(2L, 2L), Pair(2L, 3L), Pair(3L, 2L), Pair(3L, 3L), Pair(3L, 4L))

        // テストデータの登録
        insertDatas(itemsBaseList, attributesBaseList, itemAttributePairs)
    }

    private fun insertDatas(
        itemsBaseList: List<ItemsBase>,
        attributesBaseList: List<AttributesBase>,
        itemAttributesPairs: List<Pair<Long, Long>>
    ) {
        chainsBaseMapper.insert(
            ChainsBase(
                chainId = 1,
                name = "テストチェーン1",
                createdAt = LocalDateTime.of(2000, 1, 2, 3, 4),
                updatedAt = LocalDateTime.of(2000, 1, 2, 3, 4)
            )
        )
        shopsBaseMapper.insert(
            ShopsBase(
                shopId = 1,
                chainId = 1,
                name = "テストショップ1",
                createdAt = LocalDateTime.of(2000, 1, 2, 3, 4),
                updatedAt = LocalDateTime.of(2000, 1, 2, 3, 4),
            )
        )
        itemsBaseMapper.insertMultiple(itemsBaseList)
        attributesBaseMapper.insertMultiple(attributesBaseList)
        itemAttributesMappler.insertMultiple(
            itemAttributesPairs.map { pair ->
                ItemAttributesBase(
                    itemId = pair.first,
                    attributeId = pair.second,
                    createdAt = LocalDateTime.of(2000, 1, 2, 3, 4),
                    updatedAt = LocalDateTime.of(2000, 1, 2, 3, 4),
                )
            }
        )
    }

    private fun assert_パラメータで渡した商品の情報がDBから正常に取得できること(
        results: List<OrderItem>,
        orderItemParams: List<OrderItemParam>
    ) {
        assertThat(results.size).isEqualTo(3)
        assertThat(results[0].itemId).isEqualTo(orderItemParams[0].itemId)
        assertThat(results[0].name).isEqualTo("テスト商品1")
        assertThat(results[0].price).isEqualTo(orderItemParams[0].price)
        assertThat(results[0].attributes.size).isEqualTo(1)
        assertThat(results[0].attributes[0].id).isEqualTo(1)
        assertThat(results[0].attributes[0].name).isEqualTo("属性名1")
        assertThat(results[0].attributes[0].value).isEqualTo("属性値1")

        assertThat(results[1].itemId).isEqualTo(orderItemParams[1].itemId)
        assertThat(results[1].name).isEqualTo("テスト商品2")
        assertThat(results[1].price).isEqualTo(orderItemParams[1].price)
        assertThat(results[1].attributes.size).isEqualTo(2)
        assertThat(results[1].attributes[0].id).isEqualTo(2)
        assertThat(results[1].attributes[0].name).isEqualTo("属性名2")
        assertThat(results[1].attributes[0].value).isEqualTo("属性値2")
        assertThat(results[1].attributes[1].id).isEqualTo(3)
        assertThat(results[1].attributes[1].name).isEqualTo("属性名3")
        assertThat(results[1].attributes[1].value).isEqualTo("属性値3")

        assertThat(results[2].itemId).isEqualTo(orderItemParams[2].itemId)
        assertThat(results[2].name).isEqualTo("テスト商品3")
        assertThat(results[2].price).isEqualTo(orderItemParams[2].price)
        assertThat(results[2].attributes.size).isEqualTo(3)
        assertThat(results[2].attributes[0].id).isEqualTo(2)
        assertThat(results[2].attributes[0].name).isEqualTo("属性名2")
        assertThat(results[2].attributes[0].value).isEqualTo("属性値2")
        assertThat(results[2].attributes[1].id).isEqualTo(3)
        assertThat(results[2].attributes[1].name).isEqualTo("属性名3")
        assertThat(results[2].attributes[1].value).isEqualTo("属性値3")
        assertThat(results[2].attributes[2].id).isEqualTo(4)
        assertThat(results[2].attributes[2].name).isEqualTo("属性名4")
        assertThat(results[2].attributes[2].value).isEqualTo("属性値4")
    }
}