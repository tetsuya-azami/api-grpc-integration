package com.example.orderreception.domain.model.order

import com.example.orderreception.infrastructure.entity.generated.AddressesBase

data class Address private constructor(
    val userId: Long,
    val postcode: String,
    val prefecture: String,
    val city: String,
    val streetAddress: String,
    val building: String
) {
    companion object {
        fun fromBase(addressesBase: AddressesBase): Address {
            return Address(
                userId = addressesBase.userId!!,
                postcode = addressesBase.postcode!!,
                prefecture = addressesBase.prefecture!!,
                city = addressesBase.city!!,
                streetAddress = addressesBase.streetAddress!!,
                building = addressesBase.building!!
            )
        }
    }
}