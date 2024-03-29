package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass

data class OrderItemAttribute private constructor(
    val attributeId: Long,
) {
    companion object {
        fun fromOrderCreationRequest(orderItem: OrderOuterClass.Item): List<OrderItemAttribute> {
            return orderItem.attributesList.map { attribute -> OrderItemAttribute(attribute.id) }
        }
    }
}