package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass.*
import com.example.grpcinterface.proto.OrderOuterClass.Delivery
import com.example.grpcinterface.proto.OrderOuterClass.User
import java.time.LocalDateTime

class OrderModel private constructor(
    private val orderId: Long,
    private val orderItems: List<OrderItem>,
    private val chainId: Long,
    private val shopId: Long,
    private val delivery: Delivery,
    private val user: User,
    private val payment: Payment,
    private val time: LocalDateTime
) {

    companion object {
        fun fromRequest(orderCreationRequest: OrderCreationRequest): OrderModel {
            TODO("implement entity creation from request")
        }
    }
}