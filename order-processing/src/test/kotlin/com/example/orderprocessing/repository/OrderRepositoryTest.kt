package com.example.orderprocessing.repository

import com.example.orderprocessing.model.order.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import java.time.LocalDateTime

@SpringBootTest
class OrderRepositoryTest @Autowired constructor(private val orderRepository: OrderRepository) {

    private val now = LocalDateTime.of(2000, 1, 2, 3, 4, 5)

    @Test
    @Sql(
        scripts = ["sql/orders/clear.sql"],
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    fun test() {
        val order = createTestOrder()
        orderRepository.createOrder(order)
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
}

