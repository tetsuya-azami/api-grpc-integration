package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass

data class Delivery private constructor(
    var type: DeliveryType,
    var addressId: Long
) {
    companion object {
        fun fromOrderCreationRequest(order: OrderOuterClass.Order): Delivery {
            val delivery = order.delivery
            return Delivery(
                type = DeliveryType.fromString(delivery.type.name),
                addressId = delivery.addressId
            )
        }

        fun createTestDelivery(type: DeliveryType, addressId: Long): Delivery {
            return Delivery(type, addressId)
        }
    }
}