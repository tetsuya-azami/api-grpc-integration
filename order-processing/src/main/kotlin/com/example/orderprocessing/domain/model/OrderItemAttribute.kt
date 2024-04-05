package com.example.orderprocessing.domain.model

import com.example.grpcinterface.proto.OrderOuterClass.Item.Attribute
import com.example.orderprocessing.error.ValidationError
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

data class OrderItemAttribute private constructor(
    val attributeId: Long,
) {
    companion object {
        fun fromOrderCreationRequest(orderItemAttribute: Attribute): Result<OrderItemAttribute, List<ValidationError>> {
            return Ok(OrderItemAttribute(attributeId = orderItemAttribute.id))
        }
    }
}