package com.example.orderprocessing.helper

import com.example.orderprocessing.domain.model.*
import com.example.orderprocessing.infrastructure.entity.generated.OrderItemsBase
import com.example.orderprocessing.infrastructure.entity.generated.OrdersBase
import com.example.orderprocessing.presentation.order.*
import com.github.michaelbull.result.get
import org.assertj.core.api.Assertions
import java.math.BigDecimal
import java.time.LocalDateTime

class OrderTestHelper {
    companion object {
        private val now: LocalDateTime = LocalDateTime.of(2000, 1, 2, 3, 4, 5)

        fun createOrderParam(
            itemParams: List<OrderItemParam> = createDefaultOrderItemParams(),
            chainId: Long = 1,
            shopId: Long = 1,
            deliveryType: DeliveryType = DeliveryType.IMMEDIATE,
            addressId: Long = 1,
            userId: Long = 1,
            blackLevel: BlackLevel = BlackLevel.LOW,
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
                blackLevel = blackLevel.name,
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
            blackLevel: BlackLevel = BlackLevel.LOW,
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

        fun assert_作成された注文モデルが正しいこと(
            expected: OrderParam,
            actual: Order
        ) {
            Assertions.assertThat(actual.orderId).isNotNull
            Assertions.assertThat(actual.chainId).isEqualTo(expected.chainId)
            Assertions.assertThat(actual.shopId).isEqualTo(expected.shopId)
            Assertions.assertThat(actual.user.userId).isEqualTo(expected.userParam.id)
            Assertions.assertThat(actual.payment.paymentMethodType.name)
                .isEqualTo(expected.paymentParam.paymentMethod)
            Assertions.assertThat(actual.delivery.addressId).isEqualTo(expected.deliveryParam.addressId)
            Assertions.assertThat(actual.delivery.type.name).isEqualTo(expected.deliveryParam.deliveryType)
            Assertions.assertThat(actual.payment.deliveryCharge).isEqualTo(expected.paymentParam.deliveryCharge)
            Assertions.assertThat(actual.payment.nonTaxedTotalPrice).isEqualTo(expected.paymentParam.nonTaxedTotalPrice)
            Assertions.assertThat(actual.payment.tax).isEqualTo(expected.paymentParam.tax)
            Assertions.assertThat(actual.payment.taxedTotalPrice).isEqualTo(expected.paymentParam.taxedTotalPrice)
            Assertions.assertThat(actual.time).isEqualTo(expected.time)
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
            Assertions.assertThat(actual.deliveryCharge).isEqualTo(expected.payment.deliveryCharge.toLong())
            Assertions.assertThat(actual.nonTaxedTotalPrice).isEqualTo(expected.payment.nonTaxedTotalPrice.toLong())
            Assertions.assertThat(actual.tax).isEqualTo(expected.payment.tax.toLong())
            Assertions.assertThat(actual.taxedTotalPrice).isEqualTo(expected.payment.taxedTotalPrice.toLong())
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
