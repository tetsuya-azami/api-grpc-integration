package com.example.orderprocessing.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderprocessing.domain.model.DeliveryType

data class DeliveryParam(val deliveryType: DeliveryType, val addressId: Long) {
    companion object {
        fun fromProto(deliveryProto: OrderOuterClass.Delivery): DeliveryParam {
            return DeliveryParam(
                deliveryType = DeliveryType.fromString(deliveryProto.type.name),
                addressId = deliveryProto.addressId
            )
        }
    }
}