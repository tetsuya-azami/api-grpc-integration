package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass

data class User(
    private val userId: Long,
    private val blackLevel: BlackLevel
) {
    companion object {
        fun fromOrderCreationRequest(order: OrderOuterClass.Order): User {
            val user = order.user
            return User(
                userId = user.id,
                blackLevel = BlackLevel.fromString(user.blackLevel.name)
            )
        }
    }
}