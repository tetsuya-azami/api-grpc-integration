package com.example.orderprocessing.model.order

import java.util.*

enum class DeliveryType {
    IMMEDIATE,
    SPECIFIED;

    companion object {
        fun fromString(value: String): DeliveryType {
            return entries.first { it.name == value.uppercase(Locale.getDefault()) }
        }
    }
}