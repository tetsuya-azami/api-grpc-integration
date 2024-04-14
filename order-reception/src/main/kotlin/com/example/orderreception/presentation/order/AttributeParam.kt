package com.example.orderreception.presentation.order

import com.example.orderreception.error.ValidationError
import com.example.orderreception.openapi.model.Attribute
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

data class AttributeParam(val attributeId: Long) {
    companion object {
        fun fromOpenApi(attribute: Attribute): Result<AttributeParam, List<ValidationError>> {
            return if (attribute.id == null) Err(listOf(ValidationError(message = "商品属性IDがありません。")))
            else Ok(
                AttributeParam(
                    attributeId = attribute.id
                )
            )
        }
    }
}
