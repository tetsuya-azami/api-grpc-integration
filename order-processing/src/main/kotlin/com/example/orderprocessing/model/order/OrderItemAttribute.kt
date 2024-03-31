package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass.Item.Attribute

data class OrderItemAttribute private constructor(
    val attributeId: Long,
) {
    companion object {
        fun fromOrderCreationRequest(orderItemAttribute: Attribute): OrderItemAttribute {
            return OrderItemAttribute(attributeId = orderItemAttribute.id)
        }
    }
}