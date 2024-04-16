package com.example.orderreception.domain.query

import com.example.orderreception.domain.model.order.User

interface UserQuery {
    fun findById(userId: Long): User?
}