package com.example.merchantadministration.infrastructure.query

import com.example.merchantadministration.error.MerchantAdministrationIllegalArgumentException
import com.example.merchantadministration.infrastructure.entity.generated.*
import com.example.merchantadministration.infrastructure.mapper.generated.*
import com.example.merchantadministration.presentation.ItemWithSelectedAttributeIdsParam
import com.example.merchantadministration.presentation.SelectedAttributeIdParam
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.stream.Stream

@SpringBootTest
class ItemsWithSelectedAttributesCheckingQueryImplTest(
    @Autowired private val sut: ItemsWithSelectedAttributesCheckingQueryImpl,
    @Autowired private val chainsBaseMapper: ChainsBaseMapper,
    @Autowired private val shopsBaseMapper: ShopsBaseMapper,
    @Autowired private val itemsBaseMapper: ItemsBaseMapper,
    @Autowired private val attributesBaseMapper: AttributesBaseMapper,
    @Autowired private val itemAttributesBaseMapper: ItemAttributesBaseMapper
) {
    companion object {
        val now = LocalDateTime.of(2000, 1, 2, 3, 4, 5)

        @JvmStatic
        private fun illegalParams() = Stream.of(
            // itemIdが不整合
            Arguments.of(
                1000L, 2L, 3L, BigDecimal(100),
                listOf(
                    SelectedAttributeIdParam.new(4), SelectedAttributeIdParam.new(5)
                ),
                "items",
                "存在しない商品です。itemId: 1000, chainId: 2, shopId: 3"
            ),
            // chainIdが不整合
            Arguments.of(
                1L, 2000L, 3L, BigDecimal(100), listOf(
                    SelectedAttributeIdParam.new(4), SelectedAttributeIdParam.new(5)
                ),
                "items",
                "存在しない商品です。itemId: 1, chainId: 2000, shopId: 3"
            ),
            // shopIdが不整合
            Arguments.of(
                1L, 2L, 3000L, BigDecimal(100), listOf(
                    SelectedAttributeIdParam.new(4), SelectedAttributeIdParam.new(5)
                ),
                "items",
                "存在しない商品です。itemId: 1, chainId: 2, shopId: 3000"
            ),
            // priceが不整合
            Arguments.of(
                1L, 2L, 3L, BigDecimal(1000), listOf(
                    SelectedAttributeIdParam.new(4), SelectedAttributeIdParam.new(5)
                ),
                "price",
                "マスタデータと価格が一致しません。itemId: 1, chainId: 2, shopId: 3, マスタデータのprice: 100, パラメータのprice: 1000"
            ),
            // selectedAttributeIdsが不整合
            Arguments.of(
                1L, 2L, 3L, BigDecimal(100), listOf(
                    SelectedAttributeIdParam.new(4000), SelectedAttributeIdParam.new(5)
                ),
                "itemAttributes",
                "存在しない属性が含まれています。itemId: 1, attributeIds: [4,5]"
            ),
        )
    }


    @BeforeEach
    fun setUp() {
        itemAttributesBaseMapper.delete { allRows() }
        attributesBaseMapper.delete { allRows() }
        itemsBaseMapper.delete { allRows() }
        shopsBaseMapper.delete { allRows() }
        chainsBaseMapper.delete { allRows() }
        chainsBaseMapper.insert(
            ChainsBase(
                chainId = 2L,
                name = "テストチェーン",
                createdAt = now,
                updatedAt = now,
            )
        )
        shopsBaseMapper.insert(
            ShopsBase(
                chainId = 2L,
                shopId = 3L,
                name = "テストショップ",
                createdAt = now,
                updatedAt = now,
            )
        )
        itemsBaseMapper.insert(
            ItemsBase(
                itemId = 1L,
                chainId = 2L,
                shopId = 3L,
                name = "テスト商品1",
                price = 100L,
                description = "テスト商品1の説明",
                createdAt = now,
                updatedAt = now,
            )
        )
        attributesBaseMapper.insertMultiple(
            AttributesBase(
                attributeId = 4L,
                name = "テスト属性1",
                value = "テスト属性1の値",
                createdAt = now,
                updatedAt = now,
            ),
            AttributesBase(
                attributeId = 5L,
                name = "テスト属性2",
                value = "テスト属性2の値",
                createdAt = now,
                updatedAt = now,
            )
        )
        itemAttributesBaseMapper.insertMultiple(
            ItemAttributesBase(
                itemId = 1L,
                attributeId = 4L,
                createdAt = now,
                updatedAt = now,
            ),
            ItemAttributesBase(
                itemId = 1L,
                attributeId = 5L,
                createdAt = now,
                updatedAt = now,
            )
        )
    }

    @Test
    fun 正常系() {
        // Given
        val params = getParams()
        // When
        assertThat(sut.checkItemsWithSelectedAttributes(params)).isTrue()
        // Then
    }

    @ParameterizedTest
    @MethodSource("illegalParams")
    fun 異常系(
        itemId: Long,
        chainId: Long,
        shopId: Long,
        price: BigDecimal,
        selectedAttributeIds: List<SelectedAttributeIdParam>,
        expectedErrorFiledName: String,
        expectedErrorMessage: String
    ) {
        // Given
        val params = listOf(
            ItemWithSelectedAttributeIdsParam.new(
                itemId = itemId,
                chainId = chainId,
                shopId = shopId,
                price = price,
                selectedAttributeIds = selectedAttributeIds
            )
        )
        // When
        val result = runCatching { sut.checkItemsWithSelectedAttributes(params) }
        // Then
        assertThat(result.isFailure).isTrue()
        val exception = result.exceptionOrNull() as MerchantAdministrationIllegalArgumentException
        assertThat(exception.validationErrors.size).isEqualTo(1)
        assertThat(exception.validationErrors[0].field).isEqualTo(expectedErrorFiledName)
    }

    private fun getParams() = listOf(
        ItemWithSelectedAttributeIdsParam.new(
            itemId = 1,
            chainId = 2,
            shopId = 3,
            price = BigDecimal(100),
            selectedAttributeIds = listOf(
                SelectedAttributeIdParam.new(4), SelectedAttributeIdParam.new(5)
            )
        ),
        ItemWithSelectedAttributeIdsParam.new(
            itemId = 1,
            chainId = 2,
            shopId = 3,
            price = BigDecimal(100),
            selectedAttributeIds = listOf(
                SelectedAttributeIdParam.new(4), SelectedAttributeIdParam.new(5)
            )
        )
    )
}