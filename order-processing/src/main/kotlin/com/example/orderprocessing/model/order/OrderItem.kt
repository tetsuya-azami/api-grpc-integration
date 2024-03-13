package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass

data class OrderItem(
    private val itemId: Long,
    private val price: Long,
    private val quantity: Int,
    private val attributes: List<OrderItemAttribute>
) {
    companion object {
        fun fromOrderCreationRequest(order: OrderOuterClass.Order): List<OrderItem> {
            return order.itemsList.map { item ->
                val attributes = OrderItemAttribute.fromOrderCreationRequest(item)

                OrderItem(
                    itemId = item.id,
                    price = item.price.toLong(),
                    quantity = item.quantity,
                    attributes = attributes
                )
            }
        }
    }
}