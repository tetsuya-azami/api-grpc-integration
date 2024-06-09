package com.example.orderprocessing.helper

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.grpcinterface.proto.OrderOuterClass.*
import com.example.grpcinterface.proto.OrderOuterClass.Delivery
import com.example.grpcinterface.proto.OrderOuterClass.OrderItem.Attribute
import com.example.grpcinterface.proto.OrderOuterClass.Payment
import com.example.grpcinterface.proto.OrderOuterClass.User
import com.example.orderprocessing.domain.model.*
import com.example.orderprocessing.domain.model.Order
import com.example.orderprocessing.domain.model.OrderItem
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
            deliveryType: Delivery.Type = Delivery.Type.IMMEDIATE,
            addressId: Long = 1,
            userId: Long = 1,
            blackLevel: User.BlackLevel = User.BlackLevel.LOW,
            paymentMethod: Payment.PaymentMethod = Payment.PaymentMethod.CASH,
            deliveryCharge: Long = 350,
            nonTaxedTotalPrice: Long = 1713,
            tax: Long = 206,
            taxedTotalPrice: Long = 2269
        ): OrderOuterClass.Order {
            return OrderOuterClass.Order.newBuilder()
                .addAllOrderItems(
                    orderItemProtos
                )
                .setChain(
                    Chain.newBuilder()
                        .setId(chainId)
                        .build()
                )
                .setShop(
                    Shop.newBuilder()
                        .setId(shopId)
                        .build()
                )
                .setDelivery(
                    Delivery.newBuilder()
                        .setType(deliveryType)
                        .setAddressId(addressId)
                        .build()
                )
                .setUser(
                    User.newBuilder()
                        .setId(userId)
                        .setBlackLevel(blackLevel)
                        .build()
                )
                .setPayment(
                    Payment.newBuilder()
                        .setPaymentMethod(paymentMethod)
                        .setDeliveryCharge(Money.newBuilder().setUnits(deliveryCharge).setCurrencyCode("JPY").build())
                        .setNonTaxedTotalPrice(
                            Money.newBuilder().setUnits(nonTaxedTotalPrice).setCurrencyCode("JPY").build()
                        )
                        .setTax(Money.newBuilder().setUnits(tax).setCurrencyCode("JPY").build())
                        .setTaxedTotalPrice(Money.newBuilder().setUnits(taxedTotalPrice).setCurrencyCode("JPY").build())
                        .build()
                )
                .setTime(
                    Timestamp.newBuilder()
                        .setSeconds(
                            now.toEpochSecond(ZoneOffset.of("+09:00"))
                        )
                        .build()
                )
                .build()
        }

        fun createOrderParam(
            itemParams: List<OrderItemParam> = createDefaultOrderItemParams(),
            chainId: Long = 1,
            shopId: Long = 1,
            deliveryType: DeliveryType = DeliveryType.IMMEDIATE,
            addressId: Long = 1,
            userId: Long = 1,
            blackLevel: String = BlackLevel.LOW.name,
            paymentMethod: PaymentMethodType = PaymentMethodType.CASH,
            deliveryCharge: BigDecimal = BigDecimal.valueOf(350),
            nonTaxedTotalPrice: BigDecimal = BigDecimal.valueOf(1713),
            tax: BigDecimal = BigDecimal.valueOf(206),
            taxedTotalPrice: BigDecimal = BigDecimal.valueOf(2269)
        ): OrderParam {
            return OrderParam(
                itemParams = itemParams,
                chainId = chainId,
                shopId = shopId,
                deliveryParam = DeliveryParam(
                    deliveryType = deliveryType.name,
                    addressId = addressId
                ),
                userParam = UserParam(id = userId),
                blackLevel = blackLevel,
                paymentParam = PaymentParam(
                    paymentMethod = paymentMethod.name,
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
            paymentMethod: PaymentMethodType = PaymentMethodType.CASH,
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
            ).get()!!
        }

        fun createOrderItemProto(
            id: Long = 1,
            price: Long,
            quantity: Int,
            attributeProtos: List<Attribute> = createDefaultOrderItemAttributeProtos()
        ): OrderOuterClass.OrderItem {
            return OrderOuterClass.OrderItem.newBuilder()
                .setId(id)
                .setName("テスト商品$id")
                .setPrice(
                    Money.newBuilder()
                        .setUnits(price)
                        .setCurrencyCode("JPY")
                        .build()
                )
                .addAllAttributes(
                    attributeProtos
                )
                .setQuantity(quantity)
                .build()
        }

        fun createOrderItemParam(
            id: Long = 1,
            price: BigDecimal,
            quantity: Int,
            attributeParams: List<AttributeParam> = createDefaultOrderItemAttributeParams()
        ): OrderItemParam {
            return OrderItemParam(
                id = id,
                price = price,
                name = "テスト商品${id}",
                quantity = quantity,
                attributeParams = attributeParams
            )
        }

        fun createOrderItem(
            orderItemParam: OrderItemParam
        ): OrderItem {
            return OrderItem.fromParam(orderItemParam = orderItemParam).get()!!
        }

        fun createDefaultOrderItemProtos(): List<OrderOuterClass.OrderItem> {
            return listOf(
                createOrderItemProto(
                    id = 1,
                    price = 150,
                    quantity = 1
                ),
                createOrderItemProto(
                    id = 2,
                    price = 200,
                    quantity = 2
                ),
            )
        }

        fun createDefaultOrderItemParams(): List<OrderItemParam> {
            return listOf(
                createOrderItemParam(id = 1, price = BigDecimal.valueOf(150), quantity = 1),
                createOrderItemParam(id = 2, price = BigDecimal.valueOf(472), quantity = 2),
                createOrderItemParam(id = 3, price = BigDecimal.valueOf(619), quantity = 1)
            )
        }

        private fun createDefaultOrderItems(): List<OrderItem> {
            return this.createDefaultOrderItemParams().map { createOrderItem(it) }
        }

        fun createOrderItemAttributeParam(id: Long): AttributeParam {
            return AttributeParam(
                id = id,
                name = "テスト属性名${id}",
                value = "テスト属性値${id}"
            )
        }

        private fun createDefaultOrderItemAttributeParams(): List<AttributeParam> {
            return listOf(
                createOrderItemAttributeParam(1),
                createOrderItemAttributeParam(2)
            )
        }

        fun createOrderItemAttributeProto(id: Long): Attribute {
            return Attribute.newBuilder()
                .setId(id)
                .setName("テスト属性名$id")
                .setValue("テスト属性値$id")
                .build()
        }

        private fun createDefaultOrderItemAttributeProtos(): List<Attribute> {
            return listOf(
                createOrderItemAttributeProto(1),
                createOrderItemAttributeProto(2)
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
