package com.example.orderprocessing.domain.model

import com.example.orderprocessing.error.ValidationError
import com.example.orderprocessing.presentation.order.AttributeParam
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

data class OrderItemAttribute private constructor(
    val attributeId: Long,
) {
    companion object {
        fun fromParam(attributeParam: AttributeParam): Result<OrderItemAttribute, List<ValidationError>> {
            return Ok(OrderItemAttribute(attributeId = attributeParam.id))
        }
    }
}