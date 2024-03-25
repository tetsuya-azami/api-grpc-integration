package com.example.orderprocessing.helper

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderprocessing.model.order.Order
import com.example.orderprocessing.model.order.OrderId
import com.example.orderprocessing.repository.entity.generated.OrderItemsBase
import com.example.orderprocessing.repository.entity.generated.OrdersBase
import com.google.protobuf.Timestamp
import com.google.type.Money
import org.assertj.core.api.Assertions
import java.time.LocalDateTime
import java.time.ZoneOffset

class OrderTestHelper {
    companion object {
        val now = LocalDateTime.of(2000, 1, 2, 3, 4, 5)

        fun createTestOrderItemAttribute(attributeId: Long): OrderOuterClass.Item.Attribute =
            OrderOuterClass.Item.Attribute.newBuilder()
                .setId(attributeId)
                .build()

        fun createTestOrder(items: List<OrderOuterClass.Item>): Order {
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

        fun createTestOrderItems(vararg testOrderItems: TestOrderItem): List<OrderOuterClass.Item> {
            return testOrderItems.map {
                val price = Money.newBuilder().setCurrencyCode("JPY").setUnits(it.price).build()
                OrderOuterClass.Item.newBuilder()
                    .setId(it.itemId)
                    .setName(it.name)
                    .setPrice(price)
                    .setQuantity(it.quantity)
                    .addAllAttributes(it.attributes)
                    .build()
            }
        }

        class TestOrderItem(
            val itemId: Long,
            val name: String,
            val price: Long,
            val quantity: Int,
            val attributes: List<OrderOuterClass.Item.Attribute>
        )

        fun assert_登録された注文情報が正しいこと(
            actual: OrdersBase,
            expected: Order
        ) {
            Assertions.assertThat(actual.orderId).isEqualTo(expected.orderId.value)
            Assertions.assertThat(actual.chainId).isEqualTo(expected.chainId)
            Assertions.assertThat(actual.shopId).isEqualTo(expected.shopId)
            Assertions.assertThat(actual.userId).isEqualTo(expected.user.userId)
            Assertions.assertThat(actual.paymentMethod).isEqualTo(expected.payment.paymentMethodType.name)
            Assertions.assertThat(actual.deliveryAddressId).isEqualTo(expected.delivery.addressId)
            Assertions.assertThat(actual.deliveryType).isEqualTo(expected.delivery.type.name)
            Assertions.assertThat(actual.deliveryCharge).isEqualTo(expected.payment.deliveryCharge)
            Assertions.assertThat(actual.nonTaxedTotalPrice).isEqualTo(expected.payment.nonTaxedTotalPrice)
            Assertions.assertThat(actual.tax).isEqualTo(expected.payment.tax)
            Assertions.assertThat(actual.taxedTotalPrice).isEqualTo(expected.payment.taxedTotalPrice)
            Assertions.assertThat(actual.time).isEqualTo(expected.time)
            Assertions.assertThat(actual.createdAt).isEqualTo(now)
            Assertions.assertThat(actual.updatedAt).isEqualTo(now)
        }

        fun assert_登録された注文商品が正しいこと(
            actual: OrderItemsBase,
            expectedOrderId: OrderId,
            expectedOrderItem: OrderOuterClass.Item
        ) {
            Assertions.assertThat(actual.orderId).isEqualTo(expectedOrderId.value)
            Assertions.assertThat(actual.itemId).isEqualTo(expectedOrderItem.id)
            Assertions.assertThat(actual.quantity).isEqualTo(expectedOrderItem.quantity)
            Assertions.assertThat(actual.createdAt).isEqualTo(now)
            Assertions.assertThat(actual.updatedAt).isEqualTo(now)
        }
    }
}
