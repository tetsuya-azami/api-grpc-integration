package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * 注文ルートエンティティ
 */
class Order private constructor(
    val orderId: OrderId,
    val orderItems: OrderItems,
    val chainId: Long,
    val shopId: Long,
    val delivery: Delivery,
    val user: User,
    val payment: Payment,
    val time: LocalDateTime
) {

    companion object {
        fun fromOrderCreationRequest(request: OrderOuterClass.OrderCreationRequest): Order {
            val order = request.order
            val orderItems = OrderItems.fromOrderCreationRequest(order.itemsList)
            val delivery = Delivery.fromOrderCreationRequest(order)
            val user = User.fromOrderCreationRequest(order)
            val payment = Payment.fromOrderCreationRequest(order)

            return Order(
                orderId = OrderId.new(),
                orderItems = orderItems,
                chainId = order.chain.id,
                shopId = order.shop.id,
                delivery = delivery,
                user = user,
                payment = payment,
                time = LocalDateTime.ofEpochSecond(
                    order.time.seconds,
                    order.time.nanos,
                    ZoneOffset.of("+09:00")
                )
            )
        }
    }
}