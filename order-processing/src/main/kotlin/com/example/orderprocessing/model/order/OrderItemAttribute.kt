package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass

data class OrderItemAttribute private constructor(
    val attributeId: Long,
) {
    companion object {
        fun fromOrderCreationRequest(orderItem: OrderOuterClass.Items): List<OrderItemAttribute> {
            return orderItem.attributesList.map { attribute -> OrderItemAttribute(attribute.id) }
        }

        fun createTestOrderItemAttribute(
            attributeId: Long
        ): OrderItemAttribute {
            return OrderItemAttribute(attributeId)
        }
    }
}