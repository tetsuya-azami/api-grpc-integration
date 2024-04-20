package com.example.orderreception.domain.model.order

import com.example.orderreception.infrastructure.entity.generated.ItemsBase
import java.math.BigDecimal

data class Item(val itemId: Long, val price: BigDecimal) {
    companion object {
        fun fromBase(itemsBase: ItemsBase): Item {
            return Item(itemId = itemsBase.itemId!!, price = BigDecimal.valueOf(itemsBase.price!!))
        }
    }
}