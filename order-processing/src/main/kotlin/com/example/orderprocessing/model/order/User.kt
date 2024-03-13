package com.example.orderprocessing.model.order

data class User(
    private val userId: Long,
    private val blackLevel: BlackLevel
)