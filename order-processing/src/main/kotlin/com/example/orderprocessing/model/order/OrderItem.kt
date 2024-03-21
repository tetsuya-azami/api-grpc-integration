package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass

class OrderItem private constructor(
    val itemId: Long,
    val price: Long,
    val quantity: Int,
    val attributes: List<OrderItemAttribute>
) {
    companion object {
        fun fromOrderCreationRequest(orderItem: OrderOuterClass.Item): OrderItem {
            val attributes = OrderItemAttribute.fromOrderCreationRequest(orderItem)

            return OrderItem(
                itemId = orderItem.id,
                price = orderItem.price.units,
                quantity = orderItem.quantity,
                attributes = attributes
            )
        }
    }
}