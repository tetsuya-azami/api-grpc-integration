package com.example.orderreception.usecase.query

import com.example.orderreception.domain.model.order.Item
import com.example.orderreception.presentation.order.ItemParam

interface ItemQuery {
    fun findItems(itemParams: List<ItemParam>, chainId: Long, shopId: Long): List<Item>
}