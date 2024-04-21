package com.example.orderreception.domain.model.order

import com.example.orderreception.infrastructure.entity.generated.AttributesBase

data class Attribute private constructor(
    val id: Long,
    val name: String,
    val value: String
) {
    companion object {
        fun fromBase(attributesBase: AttributesBase): Attribute {
            return Attribute(
                id = attributesBase.attributeId!!,
                name = attributesBase.name!!,
                value = attributesBase.value!!
            )
        }
    }
}