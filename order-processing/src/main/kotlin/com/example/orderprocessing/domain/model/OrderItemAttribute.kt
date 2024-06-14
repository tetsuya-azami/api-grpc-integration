package com.example.orderprocessing.domain.model

data class OrderItemAttribute private constructor(
    val itemId: Long,
    val attributeId: Long,
    val name: String,
    val value: String
) {
    companion object {
        fun new(
            itemId: Long,
            attributeId: Long,
            name: String,
            value: String
        ): OrderItemAttribute {
            return OrderItemAttribute(
                itemId = itemId,
                attributeId = attributeId,
                name = name,
                value = value
            )
        }
    }
}