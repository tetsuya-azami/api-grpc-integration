package com.example.orderreception.usecase.query

import com.example.orderreception.domain.model.order.Item
import com.example.orderreception.infrastructure.query.ItemQueryParam

interface ItemQuery {
    fun findItems(itemQueryParams: List<ItemQueryParam>): List<Item>
}