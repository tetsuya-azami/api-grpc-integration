package com.example.orderreception.presentation.order

sealed interface OrderValidationResult {
    companion object {
        fun valid() = Valid
        fun invalid(message: String) = Invalid(message)
    }

    data object Valid : OrderValidationResult
    data class Invalid(val message: String) : OrderValidationResult
}