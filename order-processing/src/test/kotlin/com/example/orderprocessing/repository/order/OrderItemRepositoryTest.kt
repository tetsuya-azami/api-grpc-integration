package com.example.orderprocessing.repository.order

import com.example.orderprocessing.model.order.OrderId
import com.example.orderprocessing.model.order.OrderItem
import com.example.orderprocessing.repository.entity.generated.OrderItemsBase
import com.example.orderprocessing.repository.mapper.generated.OrderItemsBaseMapper
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
class OrderItemRepositoryTest @Autowired constructor(
    private val orderItemRepository: OrderItemRepository,
    private val orderItemsBaseMapper: OrderItemsBaseMapper
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
        val testOrderId = 1L
        val orderItem1 = OrderItem.createTestOrderItem(itemId = 1, price = 100, quantity = 1, attributes = emptyList())
        val orderItem2 = OrderItem.createTestOrderItem(itemId = 2, price = 200, quantity = 2, attributes = emptyList())
        val orderItemList = listOf(orderItem1, orderItem2)

        // When
        orderItemRepository.createOrderItem(OrderId(testOrderId), orderItemList, now)

        // Then
        val insertedOrderItemList = orderItemsBaseMapper.select {}
        assertThat(insertedOrderItemList.size).isEqualTo(2)
        insertedOrderItemList.forEachIndexed { index, it ->
            assert_登録された注文商品が正しいこと(it, testOrderId, orderItemList, index)
        }
    }

    private fun assert_登録された注文商品が正しいこと(
        it: OrderItemsBase,
        expectedOrderId: Long,
        expectedOrderItemList: List<OrderItem>,
        index: Int
    ) {
        assertThat(it.orderId).isEqualTo(expectedOrderId)
        assertThat(it.itemId).isEqualTo(expectedOrderItemList[index].itemId)
        assertThat(it.quantity).isEqualTo(expectedOrderItemList[index].quantity)
        assertThat(it.createdAt).isEqualTo(now)
        assertThat(it.updatedAt).isEqualTo(now)
    }
}