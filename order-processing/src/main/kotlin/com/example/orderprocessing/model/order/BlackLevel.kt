package com.example.orderprocessing.model.order

enum class BlackLevel {
    LOW,
    MIDDLE,
    HIGH;

    companion object {
        fun fromString(value: String): BlackLevel {
            return entries.first { it.name == value.uppercase() }
        }
    }
}