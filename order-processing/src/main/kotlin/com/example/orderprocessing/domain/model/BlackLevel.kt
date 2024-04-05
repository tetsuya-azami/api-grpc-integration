package com.example.orderprocessing.domain.model

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