package com.example.orderprocessing.domain.model

import com.example.orderprocessing.presentation.order.UserParam

data class User private constructor(
    val userId: Long
) {
    companion object {
        fun fromParam(userParam: UserParam): User {
            return User(
                userId = userParam.id
            )
        }
    }
}