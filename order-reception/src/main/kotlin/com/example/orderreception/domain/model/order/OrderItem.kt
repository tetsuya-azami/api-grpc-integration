package com.example.orderreception.domain.model.order

import java.math.BigDecimal

data class OrderItem private constructor(
    val itemId: Long,
    val name: String,
    val price: BigDecimal,
    val attributes: List<Attribute>,
    val quantity: Int
) {
    companion object {
        fun reconstruct(
            itemId: Long,
            name: String,
            price: BigDecimal,
            attributes: List<Attribute>,
            quantity: Int
        ): OrderItem {
            return OrderItem(
                itemId = itemId,
                name = name,
                price = price,
                attributes = attributes,
                quantity = quantity
            )
        }
    }
}