package com.example.orderprocessing.domain.model

data class Delivery private constructor(
    val type: DeliveryType,
    val addressId: Long
) {
    companion object {
        fun new(
            type: DeliveryType,
            addressId: Long
        ): Delivery {
            return Delivery(
                type = type,
                addressId = addressId
            )
        }
    }
}