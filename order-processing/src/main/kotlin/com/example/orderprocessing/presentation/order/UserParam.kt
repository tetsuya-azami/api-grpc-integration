package com.example.orderprocessing.presentation.order

data class UserParam private constructor(
    val id: Long,
) {
    companion object {
        fun new(
            id: Long,
        ): UserParam {
            return UserParam(id = id)
        }
    }
}