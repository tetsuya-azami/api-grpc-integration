package com.example.orderprocessing.helper

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.grpcinterface.proto.OrderOuterClass.Payment.PaymentMethod
import com.example.orderprocessing.model.order.Order
import com.example.orderprocessing.model.order.OrderId
import com.example.orderprocessing.model.order.OrderItem
import com.example.orderprocessing.repository.entity.generated.OrderItemsBase
import com.example.orderprocessing.repository.entity.generated.OrdersBase
import com.google.protobuf.Timestamp
import com.google.type.Money
import org.assertj.core.api.Assertions
import java.time.LocalDateTime
import java.time.ZoneOffset

class OrderTestHelper {
    companion object {
        private val now: LocalDateTime = LocalDateTime.of(2000, 1, 2, 3, 4, 5)

        fun createTestOrder(
            items: List<OrderOuterClass.Item> = createDefaultTestOrderItems(),
            chainId: Long = 1,
            shopId: Long = 1,
            deliveryType: OrderOuterClass.Delivery.Type = OrderOuterClass.Delivery.Type.IMMEDIATE,
            addressId: Long = 1,
            userId: Long = 1,
            blackLevel: OrderOuterClass.User.BlackLevel = OrderOuterClass.User.BlackLevel.LOW,
            paymentMethod: PaymentMethod = OrderOuterClass.Payment.PaymentMethod.CASH,
            deliveryCharge: Long = 350,
            nonTaxedTotalPrice: Long = 1000,
            tax: Long = 135,
            taxedTotalPrice: Long = 1485
        ): Order {
            val orderProto = OrderOuterClass.Order.newBuilder()
                .addAllItems(items)
                .setChain(
                    OrderOuterClass.Chain.newBuilder()
                        .setId(chainId)
                        .build()
                )
                .setShop(
                    OrderOuterClass.Shop.newBuilder()
                        .setId(shopId)
                        .build()
                )
                .setDelivery(
                    OrderOuterClass.Delivery.newBuilder()
                        .setType(deliveryType)
                        .setAddressId(addressId)
                        .build()
                )
                .setUser(
                    OrderOuterClass.User.newBuilder()
                        .setId(userId)
                        .setBlackLevel(blackLevel)
                        .build()
                )
                .setPayment(
                    OrderOuterClass.Payment.newBuilder()
                        .setPaymentMethod(paymentMethod)
                        .setDeliveryCharge(deliveryCharge)
                        .setNonTaxedTotalPrice(nonTaxedTotalPrice)
                        .setTax(tax)
                        .setTaxedTotalPrice(taxedTotalPrice)
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

        private fun createDefaultTestOrderItems(vararg testOrderItems: TestOrderItem): List<OrderOuterClass.Item> {
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

        private fun createTestOrderItemAttribute(attributeId: Long): OrderOuterClass.Item.Attribute =
            OrderOuterClass.Item.Attribute.newBuilder()
                .setId(attributeId)
                .build()

        private fun createDefaultTestOrderItems() = createDefaultTestOrderItems(
            TestOrderItem(
                itemId = 1,
                name = "商品1",
                price = 100,
                quantity = 1,
                attributes = listOf(
                    createTestOrderItemAttribute(1),
                    createTestOrderItemAttribute(4)
                )
            ),
            TestOrderItem(
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
            expectedOrderItem: OrderItem
        ) {
            Assertions.assertThat(actual.orderId).isEqualTo(expectedOrderId.value)
            Assertions.assertThat(actual.itemId).isEqualTo(expectedOrderItem.itemId)
            Assertions.assertThat(actual.quantity).isEqualTo(expectedOrderItem.quantity)
            Assertions.assertThat(actual.createdAt).isEqualTo(now)
            Assertions.assertThat(actual.updatedAt).isEqualTo(now)
        }
    }
}
