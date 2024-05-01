package com.example.orderreception.usecase.query

import com.example.orderreception.domain.model.order.Address

interface AddressQuery {
    fun findByAddressIdAndUserId(addressId: Long, userId: Long): Address?
}