package com.example.orderprocessing.presentation.order

data class DeliveryParam(
    val deliveryType: String,
    val addressId: Long
) {
    companion object {
        fun new(
            deliveryType: String,
            addressId: Long
        ): DeliveryParam {
            return DeliveryParam(
                deliveryType = deliveryType,
                addressId = addressId
            )
        }
    }
}