package com.example.orderprocessing.presentation.order

import java.math.BigDecimal

data class OrderItemParam private constructor(
    val id: Long,
    val name: String,
    val price: BigDecimal,
    val attributeParams: List<AttributeParam>,
    val quantity: Int
) {
    companion object {
        fun new(
            id: Long,
            name: String,
            price: BigDecimal,
            attributeParams: List<AttributeParam>,
            quantity: Int
        ): OrderItemParam {
            return OrderItemParam(
                id = id,
                name = name,
                price = price,
                attributeParams = attributeParams,
                quantity = quantity
            )
        }
    }
}
