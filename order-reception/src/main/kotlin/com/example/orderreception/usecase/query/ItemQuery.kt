package com.example.orderreception.usecase.query

import com.example.orderreception.infrastructure.entity.generated.ItemsBase

interface ItemQuery {
    fun findItems(itemId: Long, chainId: Long, shopId: Long): List<ItemsBase>
}