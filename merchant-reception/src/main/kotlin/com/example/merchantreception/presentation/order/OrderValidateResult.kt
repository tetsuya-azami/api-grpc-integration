package com.example.merchantreception.presentation.order

sealed class OrderValidationResult(message: String?) {
    companion object {
        fun valid() = Valid
        fun invalid(message: String) = Invalid(message)
    }

    data object Valid : OrderValidationResult(null)
    data class Invalid(val message: String) : OrderValidationResult(message)
}