package com.example.orderprocessing.repository.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.grpcinterface.proto.OrderOuterClass.Item
import com.example.orderprocessing.model.order.Order
import com.example.orderprocessing.model.order.OrderId
import com.example.orderprocessing.repository.entity.generated.OrderItemsBase
import com.example.orderprocessing.repository.entity.generated.OrdersBase
import com.example.orderprocessing.repository.mapper.generated.OrderItemsBaseMapper
import com.example.orderprocessing.repository.mapper.generated.OrdersBaseMapper
import com.example.orderprocessing.repository.mapper.generated.select
import com.google.protobuf.Timestamp
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
import java.time.ZoneOffset

@SpringBootTest
class OrderRepositoryTest @Autowired constructor(
    private val orderRepository: OrderRepository,
    private val ordersMapper: OrdersBaseMapper,
    private val orderItemsMapper: OrderItemsBaseMapper
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
            TestOrderItem(1, "商品1", 100, 1),
            TestOrderItem(2, "商品2", 200, 2)
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
    }

    private fun createTestOrder(items: List<Item>): Order {
        val orderProto = OrderOuterClass.Order.newBuilder()
            .addAllItems(items)
            .setChain(
                OrderOuterClass.Chain.newBuilder()
                    .setId(1)
                    .build()
            )
            .setShop(
                OrderOuterClass.Shop.newBuilder()
                    .setId(1)
                    .build()
            )
            .setDelivery(
                OrderOuterClass.Delivery.newBuilder()
                    .setType(OrderOuterClass.Delivery.Type.IMMEDIATE)
                    .setAddressId(1)
                    .build()
            )
            .setUser(
                OrderOuterClass.User.newBuilder()
                    .setId(1)
                    .setBlackLevel(OrderOuterClass.User.BlackLevel.LOW)
                    .build()
            )
            .setPayment(
                OrderOuterClass.Payment.newBuilder()
                    .setPaymentMethod(OrderOuterClass.Payment.PaymentMethod.CASH)
                    .setDeliveryCharge(350)
                    .setNonTaxedTotalPrice(1000)
                    .setTax(135)
                    .setTaxedTotalPrice(1485)
                    .build()
            )
            .setTime(
                Timestamp.newBuilder()
                    .setSeconds(now.toEpochSecond(ZoneOffset.of("+09:00")))
                    .build()
            )

        return Order.fromOrderCreationRequest(
            OrderOuterClass.OrderCreationRequest.newBuilder()
                .setOrder(orderProto)
                .build()
        )
    }

    private fun createTestOrderItems(vararg testOrderItems: TestOrderItem): List<OrderOuterClass.Item> {
        return testOrderItems.map {
            val price = Money.newBuilder().setCurrencyCode("JPY").setUnits(it.price).build()
            OrderOuterClass.Item.newBuilder()
                .setId(it.itemId)
                .setName(it.name)
                .setPrice(price)
                .setQuantity(it.quantity)
                .build()
        }
    }

    private class TestOrderItem(
        val itemId: Long,
        val name: String,
        val price: Long,
        val quantity: Int,
    )

    private fun assert_登録された注文情報が正しいこと(
        actual: OrdersBase,
        expected: Order
    ) {
        assertThat(actual.orderId).isEqualTo(expected.orderId.value)
        assertThat(actual.chainId).isEqualTo(expected.chainId)
        assertThat(actual.shopId).isEqualTo(expected.shopId)
        assertThat(actual.userId).isEqualTo(expected.user.userId)
        assertThat(actual.paymentMethod).isEqualTo(expected.payment.paymentMethodType.name)
        assertThat(actual.deliveryAddressId).isEqualTo(expected.delivery.addressId)
        assertThat(actual.deliveryType).isEqualTo(expected.delivery.type.name)
        assertThat(actual.deliveryCharge).isEqualTo(expected.payment.deliveryCharge)
        assertThat(actual.nonTaxedTotalPrice).isEqualTo(expected.payment.nonTaxedTotalPrice)
        assertThat(actual.tax).isEqualTo(expected.payment.tax)
        assertThat(actual.taxedTotalPrice).isEqualTo(expected.payment.taxedTotalPrice)
        assertThat(actual.time).isEqualTo(expected.time)
        assertThat(actual.createdAt).isEqualTo(now)
        assertThat(actual.updatedAt).isEqualTo(now)
    }

    private fun assert_登録された注文商品が正しいこと(
        actual: OrderItemsBase,
        expectedOrderId: OrderId,
        expectedOrderItem: OrderOuterClass.Item
    ) {
        assertThat(actual.orderId).isEqualTo(expectedOrderId.value)
        assertThat(actual.itemId).isEqualTo(expectedOrderItem.id)
        assertThat(actual.quantity).isEqualTo(expectedOrderItem.quantity)
        assertThat(actual.createdAt).isEqualTo(now)
        assertThat(actual.updatedAt).isEqualTo(now)
    }
}

