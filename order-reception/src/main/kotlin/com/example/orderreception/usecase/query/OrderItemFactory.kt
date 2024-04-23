package com.example.orderreception.usecase.query

import com.example.orderreception.domain.model.order.OrderItem
import com.example.orderreception.presentation.order.OrderItemParam

interface OrderItemFactory {
    fun createOrderItems(orderItemParams: List<OrderItemParam>, chainId: Long, shopId: Long): List<OrderItem>
}