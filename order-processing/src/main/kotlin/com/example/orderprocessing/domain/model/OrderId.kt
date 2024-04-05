package com.example.orderprocessing.domain.model

import java.util.*

data class OrderId private constructor(val value: String) {
    companion object {
        fun new(): OrderId {
            return OrderId(UUID.randomUUID().toString())
        }
    }
}