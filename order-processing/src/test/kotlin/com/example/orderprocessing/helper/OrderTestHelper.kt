package com.example.orderprocessing.helper

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.grpcinterface.proto.OrderOuterClass.Payment.PaymentMethod
import com.example.orderprocessing.domain.model.Order
import com.example.orderprocessing.domain.model.OrderId
import com.example.orderprocessing.domain.model.OrderItem
import com.example.orderprocessing.infrastructure.entity.generated.OrderItemsBase
import com.example.orderprocessing.infrastructure.entity.generated.OrdersBase
import com.google.protobuf.Timestamp
import com.google.type.Money
import org.assertj.core.api.Assertions
import java.time.LocalDateTime
import java.time.ZoneOffset

class OrderTestHelper {
    companion object {
        private val now: LocalDateTime = LocalDateTime.of(2000, 1, 2, 3, 4, 5)

        fun createTestOrderProto(
            items: List<OrderOuterClass.Item> = ceateDefaultTestOrderItems(),
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
        ): OrderOuterClass.Order {
            return OrderOuterClass.Order.newBuilder()
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
                .build()
        }

        fun createTestOrder(
            items: List<OrderOuterClass.Item> = ceateDefaultTestOrderItems(),
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
            val orderValidationResult = Order.fromOrderCreationRequest(
                OrderOuterClass.OrderCreationRequest.newBuilder()
                    .setOrder(
                        createTestOrderProto(
                            items = items,
                            chainId = chainId,
                            shopId = shopId,
                            deliveryType = deliveryType,
                            addressId = addressId,
                            userId = userId,
                            blackLevel = blackLevel,
                            paymentMethod = paymentMethod,
                            deliveryCharge = deliveryCharge,
                            nonTaxedTotalPrice = nonTaxedTotalPrice,
                            tax = tax,
                            taxedTotalPrice = taxedTotalPrice
                        )
                    )
                    .build()
            )
            when (orderValidationResult) {
                is Order.OrderValidationResult.Success -> {
                    return orderValidationResult.order
                }

                is Order.OrderValidationResult.Failure -> {
                    throw IllegalArgumentException("バリデーションエラーが発生しました。${orderValidationResult.validationErrors}")
                }
            }
        }

        fun createTestOrderItems(vararg testOrderItems: TestOrderItem): List<OrderOuterClass.Item> {
            return testOrderItems.map {
                createTestOrderItem(
                    price = it.price,
                    itemId = it.itemId,
                    name = it.name,
                    quantity = it.quantity,
                    attributes = it.attributes
                )
            }
        }

        fun createTestOrderItem(
            price: Long,
            itemId: Long,
            name: String,
            quantity: Int,
            attributes: List<Long>
        ): OrderOuterClass.Item {
            val priceProto = Money.newBuilder().setCurrencyCode("JPY").setUnits(price).build()
            val attributesProto = attributes.map { createTestOrderItemAttribute(it) }
            return OrderOuterClass.Item.newBuilder()
                .setId(itemId)
                .setName(name)
                .setPrice(priceProto)
                .setQuantity(quantity)
                .addAllAttributes(attributesProto)
                .build()
        }

        private fun createTestOrderItemAttribute(attributeId: Long): OrderOuterClass.Item.Attribute =
            OrderOuterClass.Item.Attribute.newBuilder()
                .setId(attributeId)
                .build()

        fun ceateDefaultTestOrderItems() = createTestOrderItems(
            TestOrderItem(
                itemId = 1,
                name = "商品1",
                price = 100,
                quantity = 1,
                attributes = listOf(1, 4)
            ),
            TestOrderItem(
                itemId = 2,
                name = "商品2",
                price = 200,
                quantity = 2,
                attributes = listOf(2, 5)
            )
        )

        class TestOrderItem(
            val itemId: Long,
            val name: String,
            val price: Long,
            val quantity: Int,
            val attributes: List<Long>
        )

        fun assert_作成された注文モデルが正しいこと(
            expected: OrderOuterClass.Order,
            actual: Order
        ) {
            Assertions.assertThat(actual.orderId).isNotNull
            Assertions.assertThat(actual.chainId).isEqualTo(expected.chain.id)
            Assertions.assertThat(actual.shopId).isEqualTo(expected.shop.id)
            Assertions.assertThat(actual.user.userId).isEqualTo(expected.user.id)
            Assertions.assertThat(actual.payment.paymentMethodType.name).isEqualTo(expected.payment.paymentMethod.name)
            Assertions.assertThat(actual.delivery.addressId).isEqualTo(expected.delivery.addressId)
            Assertions.assertThat(actual.delivery.type.name).isEqualTo(expected.delivery.type.name)
            Assertions.assertThat(actual.payment.deliveryCharge).isEqualTo(expected.payment.deliveryCharge)
            Assertions.assertThat(actual.payment.nonTaxedTotalPrice).isEqualTo(expected.payment.nonTaxedTotalPrice)
            Assertions.assertThat(actual.payment.tax).isEqualTo(expected.payment.tax)
            Assertions.assertThat(actual.payment.taxedTotalPrice).isEqualTo(expected.payment.taxedTotalPrice)
            val expectedTime =
                LocalDateTime.ofEpochSecond(expected.time.seconds, 0, ZoneOffset.of("+09:00"))
            Assertions.assertThat(actual.time).isEqualTo(expectedTime)
        }

        fun assert_登録された注文情報が正しいこと(
            expected: Order,
            actual: OrdersBase
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
            expectedOrderId: OrderId,
            expectedOrderItem: OrderItem,
            actual: OrderItemsBase
        ) {
            Assertions.assertThat(actual.orderId).isEqualTo(expectedOrderId.value)
            Assertions.assertThat(actual.itemId).isEqualTo(expectedOrderItem.itemId)
            Assertions.assertThat(actual.quantity).isEqualTo(expectedOrderItem.quantity)
            Assertions.assertThat(actual.createdAt).isEqualTo(now)
            Assertions.assertThat(actual.updatedAt).isEqualTo(now)
        }
    }
}
