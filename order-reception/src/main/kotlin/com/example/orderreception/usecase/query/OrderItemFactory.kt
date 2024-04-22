package com.example.orderreception.usecase.query

import com.example.orderreception.domain.model.order.OrderItem
import com.example.orderreception.presentation.order.ItemParam

interface OrderItemFactory {
    fun createOrderItems(itemParams: List<ItemParam>, chainId: Long, shopId: Long): List<OrderItem>
}