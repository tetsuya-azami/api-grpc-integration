package com.example.orderprocessing.helper

import com.example.grpcinterface.proto.*
import com.example.grpcinterface.proto.OrderItemKt.attribute
import com.example.orderprocessing.domain.model.*
import com.example.orderprocessing.infrastructure.entity.generated.OrderItemAttributesBase
import com.example.orderprocessing.infrastructure.entity.generated.OrderItemsBase
import com.example.orderprocessing.infrastructure.entity.generated.OrdersBase
import com.example.orderprocessing.presentation.order.*
import com.github.michaelbull.result.get
import com.google.protobuf.Timestamp
import com.google.type.Money
import org.assertj.core.api.Assertions.assertThat
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZoneOffset

class OrderTestHelper {
    companion object {
        private val now: LocalDateTime = LocalDateTime.of(2000, 1, 2, 3, 4, 5)

        fun createOrderProto(
            orderItemProtos: List<OrderOuterClass.OrderItem> = createDefaultOrderItemProtos(),
            chainId: Long = 1,
            shopId: Long = 1,
            deliveryType: OrderOuterClass.Delivery.Type = OrderOuterClass.Delivery.Type.IMMEDIATE,
            deliveryAddressId: Long = 1,
            userId: Long = 1,
            userBlackLevel: OrderOuterClass.User.BlackLevel = OrderOuterClass.User.BlackLevel.LOW,
            paymentMethod: OrderOuterClass.Payment.Method = OrderOuterClass.Payment.Method.CASH,
            paymentDeliveryCharge: Long = 350,
            paymentNonTaxedTotalPrice: Long = 1713,
            patymentTax: Long = 206,
            paymentTaxedTotalPrice: Long = 2269
        ): OrderOuterClass.Order {
            return order {
                orderItems += orderItemProtos
                chain {
                    id = chainId
                }
                shop {
                    id = shopId
                }
                delivery {
                    type = deliveryType
                    addressId = deliveryAddressId
                }
                user {
                    id = userId
                    blackLevel = userBlackLevel
                }
                payment {
                    method = paymentMethod
                    deliveryCharge = Money.newBuilder()
                        .setUnits(paymentDeliveryCharge)
                        .setCurrencyCode("JPY")
                        .build()
                    nonTaxedTotalPrice = Money.newBuilder()
                        .setUnits(paymentNonTaxedTotalPrice)
                        .setCurrencyCode("JPY")
                        .build()
                    tax = Money.newBuilder()
                        .setUnits(patymentTax)
                        .setCurrencyCode("JPY")
                        .build()
                    taxedTotalPrice = Money.newBuilder()
                        .setUnits(paymentTaxedTotalPrice)
                        .setCurrencyCode("JPY")
                        .build()
                }
                time = Timestamp.newBuilder()
                    .setSeconds(
                        now.toEpochSecond(ZoneOffset.of("+09:00"))
                    )
                    .build()
            }
        }

        fun createOrderParam(
            itemParams: List<OrderItemParam> = createDefaultOrderItemParams(),
            chainId: Long = 1,
            shopId: Long = 1,
            deliveryType: String = DeliveryType.IMMEDIATE.name,
            addressId: Long = 1,
            userId: Long = 1,
            blackLevel: String = BlackLevel.LOW.name,
            paymentMethod: String = PaymentMethodType.CASH.name,
            deliveryCharge: BigDecimal = BigDecimal.valueOf(350),
            nonTaxedTotalPrice: BigDecimal = BigDecimal.valueOf(1713),
            tax: BigDecimal = BigDecimal.valueOf(206),
            taxedTotalPrice: BigDecimal = BigDecimal.valueOf(2269)
        ): OrderParam {
            return OrderParam.new(
                orderItemParams = itemParams,
                chainId = chainId,
                shopId = shopId,
                deliveryParam = DeliveryParam.new(
                    deliveryType = deliveryType,
                    addressId = addressId
                ),
                userParam = UserParam.new(id = userId),
                blackLevel = blackLevel,
                paymentParam = PaymentParam.new(
                    paymentMethod = paymentMethod,
                    deliveryCharge = deliveryCharge,
                    nonTaxedTotalPrice = nonTaxedTotalPrice,
                    tax = tax,
                    taxedTotalPrice = taxedTotalPrice
                ),
                time = now
            )
        }

        fun createOrder(
            itemParams: List<OrderItemParam> = createDefaultOrderItemParams(),
            chainId: Long = 1,
            shopId: Long = 1,
            deliveryType: DeliveryType = DeliveryType.IMMEDIATE,
            addressId: Long = 1,
            userId: Long = 1,
            blackLevel: String = BlackLevel.LOW.name,
            paymentMethod: String = PaymentMethodType.CASH.name,
            deliveryCharge: BigDecimal = BigDecimal.valueOf(350),
            nonTaxedTotalPrice: BigDecimal = BigDecimal.valueOf(1713),
            tax: BigDecimal = BigDecimal.valueOf(206),
            taxedTotalPrice: BigDecimal = BigDecimal.valueOf(2269)
        ): Order {
            return Order.fromParam(
                createOrderParam(
                    itemParams = itemParams,
                    chainId = chainId,
                    shopId = shopId,
                    deliveryType = deliveryType.name,
                    addressId = addressId,
                    userId = userId,
                    blackLevel = blackLevel,
                    paymentMethod = paymentMethod,
                    deliveryCharge = deliveryCharge,
                    nonTaxedTotalPrice = nonTaxedTotalPrice,
                    tax = tax,
                    taxedTotalPrice = taxedTotalPrice
                )
            ).get()!!
        }

        private fun createOrderItemProto(
            itemId: Long = 1,
            orderItemPrice: Long,
            orderItemQuantity: Int,
            attributeProtos: List<OrderOuterClass.OrderItem.Attribute> = createDefaultOrderItemAttributeProtos()
        ): OrderOuterClass.OrderItem {
            return orderItem {
                id = itemId
                name = "テスト商品$itemId"
                price = Money.newBuilder()
                    .setUnits(orderItemPrice)
                    .setCurrencyCode("JPY")
                    .build()
                attributes += attributeProtos
                quantity = orderItemQuantity
            }
        }

        fun createOrderItemParam(
            id: Long = 1,
            price: BigDecimal,
            quantity: Int,
            attributeParams: List<AttributeParam> = createDefaultOrderItemAttributeParams()
        ): OrderItemParam {
            return OrderItemParam.new(
                id = id,
                price = price,
                name = "テスト商品${id}",
                quantity = quantity,
                attributeParams = attributeParams
            )
        }

        private fun createDefaultOrderItemProtos(): List<OrderOuterClass.OrderItem> {
            return listOf(
                createOrderItemProto(
                    itemId = 1,
                    orderItemPrice = 150,
                    orderItemQuantity = 1
                ),
                createOrderItemProto(
                    itemId = 2,
                    orderItemPrice = 200,
                    orderItemQuantity = 2
                ),
            )
        }

        private fun createDefaultOrderItemParams(): List<OrderItemParam> {
            return listOf(
                createOrderItemParam(id = 1, price = BigDecimal.valueOf(150), quantity = 1),
                createOrderItemParam(id = 2, price = BigDecimal.valueOf(472), quantity = 2),
                createOrderItemParam(id = 3, price = BigDecimal.valueOf(619), quantity = 1)
            )
        }

        private fun createDefaultOrderItemAttributeParams(): List<AttributeParam> {
            return listOf(
                AttributeParam.new(
                    id = 1,
                    name = "テスト属性名1",
                    value = "テスト属性値1"
                ),
                AttributeParam.new(
                    id = 2,
                    name = "テスト属性名2",
                    value = "テスト属性値2"
                )
            )
        }

        private fun createDefaultOrderItemAttributeProtos(): List<OrderOuterClass.OrderItem.Attribute> {
            return listOf(
                attribute {
                    id = 1
                    name = "テスト属性名1"
                    value = "テスト属性値1"
                },
                attribute {
                    id = 2
                    name = "テスト属性名2"
                    value = "テスト属性値2"
                },
            )
        }

        fun assert_作成された注文モデルが正しいこと(
            expected: OrderParam,
            actual: Order
        ) {
            assertThat(actual.orderId).isNotNull
            assertThat(actual.chainId).isEqualTo(expected.chainId)
            assertThat(actual.shopId).isEqualTo(expected.shopId)
            assertThat(actual.user.userId).isEqualTo(expected.userParam.id)
            assertThat(actual.payment.paymentMethodType.name)
                .isEqualTo(expected.paymentParam.paymentMethod)
            assertThat(actual.delivery.addressId).isEqualTo(expected.deliveryParam.addressId)
            assertThat(actual.delivery.type.name).isEqualTo(expected.deliveryParam.deliveryType)
            assertThat(actual.payment.deliveryCharge).isEqualTo(expected.paymentParam.deliveryCharge)
            assertThat(actual.payment.nonTaxedTotalPrice).isEqualTo(expected.paymentParam.nonTaxedTotalPrice)
            assertThat(actual.payment.tax).isEqualTo(expected.paymentParam.tax)
            assertThat(actual.payment.taxedTotalPrice).isEqualTo(expected.paymentParam.taxedTotalPrice)
            assertThat(actual.time).isEqualTo(expected.time)
        }

        fun assert_登録された注文情報が正しいこと(
            expected: Order,
            actual: OrdersBase
        ) {
            assertThat(actual.orderId).isEqualTo(expected.orderId.value)
            assertThat(actual.chainId).isEqualTo(expected.chainId)
            assertThat(actual.shopId).isEqualTo(expected.shopId)
            assertThat(actual.userId).isEqualTo(expected.user.userId)
            assertThat(actual.paymentMethod).isEqualTo(expected.payment.paymentMethodType.name)
            assertThat(actual.deliveryAddressId).isEqualTo(expected.delivery.addressId)
            assertThat(actual.deliveryType).isEqualTo(expected.delivery.type.name)
            assertThat(actual.deliveryCharge).isEqualTo(expected.payment.deliveryCharge.toLong())
            assertThat(actual.nonTaxedTotalPrice).isEqualTo(expected.payment.nonTaxedTotalPrice.toLong())
            assertThat(actual.tax).isEqualTo(expected.payment.tax.toLong())
            assertThat(actual.taxedTotalPrice).isEqualTo(expected.payment.taxedTotalPrice.toLong())
            assertThat(actual.time).isEqualTo(expected.time)
            assertThat(actual.createdAt).isEqualTo(now)
            assertThat(actual.updatedAt).isEqualTo(now)
        }

        fun assert_登録された注文商品が正しいこと(
            expectedOrderId: OrderId,
            expectedOrderItem: OrderItem,
            actual: OrderItemsBase
        ) {
            assertThat(actual.orderId).isEqualTo(expectedOrderId.value)
            assertThat(actual.itemId).isEqualTo(expectedOrderItem.itemId)
            assertThat(actual.quantity).isEqualTo(expectedOrderItem.quantity)
            assertThat(actual.createdAt).isEqualTo(now)
            assertThat(actual.updatedAt).isEqualTo(now)
        }

        fun assert_登録された注文商品属性が正しいこと(
            expectedOrderId: OrderId,
            expectedOrderItem: OrderItem,
            expectedOrderItemAttribute: OrderItemAttribute,
            actual: OrderItemAttributesBase
        ) {
            assertThat(actual.orderId).isEqualTo(expectedOrderId.value)
            assertThat(actual.itemId).isEqualTo(expectedOrderItem.itemId)
            assertThat(actual.attributeId).isEqualTo(expectedOrderItemAttribute.attributeId)
            assertThat(actual.createdAt).isEqualTo(now)
            assertThat(actual.updatedAt).isEqualTo(now)
        }
    }
}
