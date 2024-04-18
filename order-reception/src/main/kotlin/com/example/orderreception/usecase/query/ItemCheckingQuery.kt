package com.example.orderreception.usecase.query

import com.example.orderreception.infrastructure.entity.generated.ItemsBase

interface ItemCheckingQuery {
    fun existsItem(itemId: Long, chainId: Long, shopId: Long): ItemsBase?
}