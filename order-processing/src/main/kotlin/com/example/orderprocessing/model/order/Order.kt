package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * 注文エンティティ
 */
class Order private constructor(
    private val orderId: Long = 0L,
    private val orderItems: List<OrderItem>,
    private val chainId: Long,
    private val shopId: Long,
    private val delivery: Delivery,
    private val user: User,
    private val payment: Payment,
    private val time: LocalDateTime
) {

    companion object {
        fun fromOrderCreationRequest(request: OrderOuterClass.OrderCreationRequest): Order {
            val orderItems = request.order.itemsList.map { item ->
                val attributes = item.attributesList.map { attribute -> OrderItemAttribute(attribute.id) }

                OrderItem(
                    itemId = item.id,
                    price = item.price.toLong(),
                    quantity = item.quantity,
                    attributes = attributes
                )
            }
            val delivery = Delivery(
                type = DeliveryType.fromString(request.order.delivery.type.name),
                addressId = request.order.delivery.addressId
            )
            val user = User(
                userId = request.order.user.id,
                blackLevel = BlackLevel.fromString(request.order.user.blackLevel.name)
            )
            val payment = Payment(
                paymentMethodType = PaymentMethodType.fromString(request.order.payment.paymentMethod.name),
                deliveryCharge = request.order.payment.deliveryCharge,
                nonTaxedTotalPrice = request.order.payment.nonTaxedTotalPrice,
                tax = request.order.payment.tax,
                taxedTotalPrice = request.order.payment.taxedTotalPrice
            )

            return Order(
                orderItems = orderItems,
                chainId = request.order.chain.id,
                shopId = request.order.shop.id,
                delivery = delivery,
                user = user,
                payment = payment,
                time = LocalDateTime.ofEpochSecond(
                    request.order.time.seconds,
                    request.order.time.nanos,
                    ZoneOffset.of("+09:00")
                )
            )
        }
    }
}