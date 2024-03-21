package com.example.orderprocessing.repository.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.grpcinterface.proto.OrderOuterClass.OrderCreationRequest
import com.example.orderprocessing.model.order.Order
import com.example.orderprocessing.repository.entity.generated.OrdersBase
import com.example.orderprocessing.repository.mapper.generated.OrdersBaseMapper
import com.example.orderprocessing.repository.mapper.generated.select
import com.google.protobuf.Timestamp
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
        scripts = ["sql/clear.sql"],
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    fun insertTest() {
        // Given
        val order = OrderOuterClass.Order.newBuilder()
            .setChain(OrderOuterClass.Chain.newBuilder().setId(1).build())
            .setShop(OrderOuterClass.Shop.newBuilder().setId(1).build())
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
            .setTime(Timestamp.newBuilder().setSeconds(now.toEpochSecond(ZoneOffset.of("+09:00"))).build())
        val orderCreationRequest = OrderCreationRequest.newBuilder().setOrder(order).build()
        val orderModel = Order.fromOrderCreationRequest(orderCreationRequest)

        // When
        val insertedOrderId = orderRepository.registerOrder(orderModel, now)
        assertThat(insertedOrderId).isEqualTo(orderModel.orderId)

        // Then
        val insertedOrderList = ordersBaseMapper.select {}
        assertThat(insertedOrderList.size).isEqualTo(1)
        val insertedOrder = insertedOrderList.first()

        assert_登録された注文情報が正しいこと(insertedOrder, orderModel)
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

