package com.example.orderprocessing.repository

import com.example.orderprocessing.model.order.*
import com.example.orderprocessing.repository.entity.generated.OrdersBase
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
    private val ordersBaseMapper: OrdersBaseMapper
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
        scripts = ["sql/orders/clear.sql"],
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    fun insertTest() {
        // Given
        val order = createTestOrder()

        // When
        orderRepository.createOrder(order)

        // Then
        val insertedOrderList = ordersBaseMapper.select {}
        assertThat(insertedOrderList.size).isEqualTo(1)
        val insertedOrder = insertedOrderList.first()

        assert_登録された注文情報が正しいこと(insertedOrder, order)
    }

    private fun createTestOrder(): Order {
        val orderItems = createTestOrderItems()
        val delivery = Delivery.createTestDelivery(type = DeliveryType.IMMEDIATE, addressId = 1)
        val user = User.createTestUser(userId = 1, blackLevel = BlackLevel.LOW)
        val payment = Payment.createTestPayment(
            paymentMethodType = PaymentMethodType.CASH,
            deliveryCharge = 350,
            nonTaxedTotalPrice = 1000,
            tax = 135,
            taxedTotalPrice = 1485
        )

        return Order.createTestOrder(
            orderId = OrderId(1),
            orderItems = orderItems,
            chainId = 1,
            shopId = 1,
            delivery = delivery,
            user = user,
            payment = payment,
            time = now
        )
    }

    private fun createTestOrderItems(): List<OrderItem> {
        val item1Attributes = listOf(OrderItemAttribute(1), OrderItemAttribute(2))
        val orderItem1 = OrderItem(1, 100, 2, item1Attributes)
        val item2Attributes = listOf(OrderItemAttribute(1), OrderItemAttribute(3))
        val orderItem2 = OrderItem(2, 200, 1, item2Attributes)

        return listOf(orderItem1, orderItem2)
    }


    private fun assert_登録された注文情報が正しいこと(
        actual: OrdersBase,
        expected: Order
    ) {
        assertThat(actual.orderId).isNotNull()
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
}

