package com.example.orderprocessing.model.order

data class Delivery(
    var type: DeliveryType,
    var addressId: Long
)