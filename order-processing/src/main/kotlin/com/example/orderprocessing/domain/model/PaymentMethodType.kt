package com.example.orderprocessing.domain.model

enum class PaymentMethodType {
    CASH,
    CREDIT,
    PAYPAY,
    AMAZONPAY,
    GOOGLEPAY,
    APPLEPAY;

    companion object {
        fun fromString(value: String): PaymentMethodType {
            return entries.first { it.name == value.uppercase() }
        }
    }
}