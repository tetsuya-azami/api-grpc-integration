package com.example.orderprocessing.repository.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderprocessing.model.order.OrderId
import com.example.orderprocessing.model.order.OrderItem
import com.example.orderprocessing.repository.entity.generated.OrderItemsBase
import com.example.orderprocessing.repository.mapper.generated.OrderItemsBaseMapper
import com.example.orderprocessing.repository.mapper.generated.select
import com.google.type.Money
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
import java.util.*

@SpringBootTest
class OrderItemRepositoryTest @Autowired constructor(
    private val orderItemRepository: OrderItemRepository,
    private val orderItemsBaseMapper: OrderItemsBaseMapper
) {
    private val now = LocalDateTime.of(2000, 1, 2, 3, 4, 5)
    private val testOrderId = "1"

    @BeforeEach
    fun setUp() {
        mockkStatic(LocalDateTime::class)
        mockkStatic(UUID::class)
        every { LocalDateTime.now() } returns now
        every { UUID.randomUUID().toString() } returns testOrderId
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
        val orderItemList = createTestOrderItemList(
            TestOrderItem(1, "商品1", 100, 1),
            TestOrderItem(2, "商品2", 200, 2)
        )

        // When
        orderItemRepository.registerOrderItems(OrderId.new(), orderItemList, now)

        // Then
        val insertedOrderItemList = orderItemsBaseMapper.select {}
        assertThat(insertedOrderItemList.size).isEqualTo(2)
        insertedOrderItemList.forEachIndexed { index, it ->
            assert_登録された注文商品が正しいこと(it, testOrderId, orderItemList, index)
        }
    }

    private fun createTestOrderItemList(vararg testOrderItems: TestOrderItem): List<OrderItem> {
        val protoOrderItemList = testOrderItems.map {
            val price = Money.newBuilder().setCurrencyCode("JPY").setUnits(it.price).build()
            OrderOuterClass.Item.newBuilder()
                .setId(it.itemId)
                .setName(it.name)
                .setPrice(price)
                .setQuantity(it.quantity)
                .build()
        }
        val orderItemList = OrderItem.fromOrderCreationRequest(protoOrderItemList)

        return orderItemList
    }

    private fun assert_登録された注文商品が正しいこと(
        it: OrderItemsBase,
        expectedOrderId: String,
        expectedOrderItemList: List<OrderItem>,
        index: Int
    ) {
        assertThat(it.orderId).isEqualTo(expectedOrderId)
        assertThat(it.itemId).isEqualTo(expectedOrderItemList[index].itemId)
        assertThat(it.quantity).isEqualTo(expectedOrderItemList[index].quantity)
        assertThat(it.createdAt).isEqualTo(now)
        assertThat(it.updatedAt).isEqualTo(now)
    }

    private class TestOrderItem(
        val itemId: Long,
        val name: String,
        val price: Long,
        val quantity: Int,
    )
}