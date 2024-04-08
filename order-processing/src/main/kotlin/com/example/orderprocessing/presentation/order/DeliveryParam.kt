package com.example.orderprocessing.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass

data class DeliveryParam(val deliveryType: String, val addressId: Long) {
    companion object {
        fun fromProto(deliveryProto: OrderOuterClass.Delivery): DeliveryParam {
            return DeliveryParam(
                deliveryType = deliveryProto.type.name,
                addressId = deliveryProto.addressId
            )
        }
    }
}