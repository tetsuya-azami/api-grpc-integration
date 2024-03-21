package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass

class OrderItem private constructor(
    val itemId: Long,
    val price: Long,
    val quantity: Int,
    val attributes: List<OrderItemAttribute>
) {
    companion object {
        fun fromOrderCreationRequest(itemList: List<OrderOuterClass.Item>): List<OrderItem> {
            return itemList.map { item ->
                val attributes = OrderItemAttribute.fromOrderCreationRequest(item)

                OrderItem(
                    itemId = item.id,
                    price = item.price.units,
                    quantity = item.quantity,
                    attributes = attributes
                )
            }
        }
    }
}