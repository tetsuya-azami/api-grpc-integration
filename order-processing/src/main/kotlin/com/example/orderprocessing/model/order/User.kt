package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass

data class User private constructor(
    val userId: Long
) {
    companion object {
        fun fromOrderCreationRequest(order: OrderOuterClass.Order): User {
            val user = order.user
            return User(
                userId = user.id
            )
        }
    }
}