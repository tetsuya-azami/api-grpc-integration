package com.example.orderprocessing.domain.model

data class User private constructor(
    val userId: Long
) {
    companion object {
        fun new(
            userId: Long
        ): User {
            return User(
                userId = userId
            )
        }
    }
}