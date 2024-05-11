package com.example.orderreception.domain.model.order

data class Attribute private constructor(
    val id: Long,
    val name: String,
    val value: String
) {
    companion object {
        fun reconstruct(
            id: Long,
            name: String,
            value: String
        ): Attribute {
            return Attribute(
                id = id,
                name = name,
                value = value
            )
        }
    }
}