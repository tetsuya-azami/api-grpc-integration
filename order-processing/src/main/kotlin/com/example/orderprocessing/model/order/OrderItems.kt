package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass

data class OrderItems private constructor(val value: List<OrderItem>) {

    companion object {
        fun fromOrderCreationRequest(itemList: List<OrderOuterClass.Item>): OrderItems {
            val orderItems = itemList.map { OrderItem.fromOrderCreationRequest(it) }
            return OrderItems(orderItems)
        }
    }

    // TODO: Add a method to calculate the total price of the order items
}