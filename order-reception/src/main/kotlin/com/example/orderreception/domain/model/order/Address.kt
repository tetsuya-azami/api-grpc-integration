package com.example.orderreception.domain.model.order

data class Address private constructor(
    val userId: Long,
    val postcode: String,
    val prefecture: String,
    val city: String,
    val streetAddress: String,
    val building: String
) {
    companion object {
        fun reconstruct(
            userId: Long,
            postcode: String,
            prefecture: String,
            city: String,
            streetAddress: String,
            building: String
        ): Address {
            return Address(
                userId = userId,
                postcode = postcode,
                prefecture = prefecture,
                city = city,
                streetAddress = streetAddress,
                building = building,
            )
        }
    }
}