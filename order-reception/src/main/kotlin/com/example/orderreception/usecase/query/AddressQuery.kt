package com.example.orderreception.usecase.query

import com.example.orderreception.domain.model.order.Address

interface AddressQuery {
    fun findById(addressId: Long, userId: Long): Address?
}