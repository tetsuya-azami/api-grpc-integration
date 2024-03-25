package com.example.orderprocessing.repository.order

import com.example.orderprocessing.helper.OrderTestHelper
import com.example.orderprocessing.helper.OrderTestHelper.Companion.assert_登録された注文商品が正しいこと
import com.example.orderprocessing.helper.OrderTestHelper.Companion.assert_登録された注文情報が正しいこと
import com.example.orderprocessing.helper.OrderTestHelper.Companion.createTestOrder
import com.example.orderprocessing.helper.OrderTestHelper.Companion.createTestOrderItemAttribute
import com.example.orderprocessing.helper.OrderTestHelper.Companion.createTestOrderItems
import com.example.orderprocessing.repository.mapper.generated.OrderItemAttributesBaseMapper
import com.example.orderprocessing.repository.mapper.generated.OrderItemsBaseMapper
import com.example.orderprocessing.repository.mapper.generated.OrdersBaseMapper
import com.example.orderprocessing.repository.mapper.generated.select
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import java.time.LocalDateTime

@SpringBootTest
class OrderRepositoryTest @Autowired constructor(
    private val orderRepository: OrderRepository,
    private val ordersMapper: OrdersBaseMapper,
    private val orderItemsMapper: OrderItemsBaseMapper,
    private val orderItemAttributeMapper: OrderItemAttributesBaseMapper
) {

    private val now = LocalDateTime.of(2000, 1, 2, 3, 4, 5)

    @BeforeEach
    fun setUp() {
        mockkStatic(LocalDateTime::class)
        every { LocalDateTime.now() } returns now
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    @Sql(
        scripts = ["sql/orders_master.sql"],
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(
        scripts = ["sql/clear.sql"],
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    fun insertTest() {
        // Given
        val orderItemList = createTestOrderItems(
            OrderTestHelper.Companion.TestOrderItem(
                itemId = 1,
                name = "商品1",
                price = 100,
                quantity = 1,
                attributes = listOf(
                    createTestOrderItemAttribute(1),
                    createTestOrderItemAttribute(4)
                )
            ),
            OrderTestHelper.Companion.TestOrderItem(
                itemId = 2,
                name = "商品2",
                price = 200,
                quantity = 2,
                attributes = listOf(
                    createTestOrderItemAttribute(2),
                    createTestOrderItemAttribute(5)
                )
            )
        )
        val order = createTestOrder(orderItemList)

        // When
        val insertedOrderId = orderRepository.registerOrder(order, now)
        assertThat(insertedOrderId).isEqualTo(order.orderId)

        // Then Orders
        val insertedOrderList = ordersMapper.select {}
        assertThat(insertedOrderList.size).isEqualTo(1)
        val insertedOrder = insertedOrderList.first()

        assert_登録された注文情報が正しいこと(insertedOrder, order)

        // Then OrderItems
        val insertedOrderItemBases = orderItemsMapper.select { }
        assertThat(insertedOrderItemBases.size).isEqualTo(2)
        insertedOrderItemBases.forEachIndexed { index, orderItemsBase ->
            assert_登録された注文商品が正しいこと(
                actual = orderItemsBase,
                expectedOrderId = insertedOrderId,
                expectedOrderItem = orderItemList[index]
            )
        }

        // Then OrderItemAttributes
        val insertedOrderItemAttributes = orderItemAttributeMapper.select { }
        assertThat(insertedOrderItemAttributes.size).isEqualTo(4)

        val expectedItemIdAndAttributeIdPairs = listOf(
            Pair(1L, 1L),
            Pair(1L, 4L),
            Pair(2L, 2L),
            Pair(2L, 5L)
        )

        expectedItemIdAndAttributeIdPairs.forEachIndexed { index, (expectedItemId, expectedAttributeId) ->
            assertThat(insertedOrderItemAttributes[index].orderId).isEqualTo(insertedOrderId.value)
            assertThat(insertedOrderItemAttributes[index].itemId).isEqualTo(expectedItemId)
            assertThat(insertedOrderItemAttributes[index].attributeId).isEqualTo(expectedAttributeId)
            assertThat(insertedOrderItemAttributes[index].createdAt).isEqualTo(now)
            assertThat(insertedOrderItemAttributes[index].updatedAt).isEqualTo(now)
        }
    }
}

