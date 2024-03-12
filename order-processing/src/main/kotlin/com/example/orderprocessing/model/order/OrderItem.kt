package com.example.orderprocessing.model.order

data class OrderItem(
    private val itemId: Long,
    private val price: Long,
    private val quantity: Int,
    private val attributes: List<OrderItemAttribute>
)