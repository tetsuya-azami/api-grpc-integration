package com.example.orderreception.domain.model.order

import java.time.LocalDateTime

class Order private constructor(
    val orderItems: List<OrderItem>,
    val chainId: Long,
    val shopId: Long,
    val delivery: Delivery,
    val userId: Long,
    val payment: Payment,
    val time: LocalDateTime
) {
    companion object {
        fun new(
            orderItem: List<OrderItem>,
            chainId: Long,
            shopId: Long,
            delivery: Delivery,
            userId: Long,
            payment: Payment,
            time: LocalDateTime
        ): Order {
            return Order(
                orderItems = orderItem,
                chainId = chainId,
                shopId = shopId,
                delivery = delivery,
                userId = userId,
                payment = payment,
                time = time,
            )
        }
    }
}