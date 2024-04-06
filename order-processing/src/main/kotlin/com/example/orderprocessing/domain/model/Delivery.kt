package com.example.orderprocessing.domain.model

import com.example.orderprocessing.presentation.order.DeliveryParam

data class Delivery private constructor(
    var type: DeliveryType,
    var addressId: Long
) {
    companion object {
        fun fromParam(deliveryParam: DeliveryParam): Delivery {
            return Delivery(
                type = deliveryParam.deliveryType,
                addressId = deliveryParam.addressId
            )
        }
    }
}