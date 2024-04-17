package com.example.orderreception.usecase.query

import com.example.orderreception.domain.model.order.User

interface UserQuery {
    fun findById(userId: Long): User?
}