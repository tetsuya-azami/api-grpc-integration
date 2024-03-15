package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass

data class OrderItem(
    val itemId: Long,
    val price: Long,
    val quantity: Int,
    val attributes: List<OrderItemAttribute>
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

        fun createTestOrderItem(
            itemId: Long,
            price: Long,
            quantity: Int,
            attributes: List<OrderItemAttribute>
        ): OrderItem {
            return OrderItem(
                itemId = itemId,
                price = price,
                quantity = quantity,
                attributes = attributes
            )
        }
    }
}