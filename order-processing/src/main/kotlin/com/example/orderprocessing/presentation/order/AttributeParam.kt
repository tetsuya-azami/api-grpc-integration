package com.example.orderprocessing.presentation.order

data class AttributeParam(
    val id: Long,
    val name: String,
    val value: String
) {
    companion object {
        fun new(
            id: Long,
            name: String,
            value: String
        ): AttributeParam {
            return AttributeParam(
                id = id,
                name = name,
                value = value
            )
        }
    }
}
