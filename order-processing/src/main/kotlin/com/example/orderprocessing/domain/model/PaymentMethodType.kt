package com.example.orderprocessing.domain.model

import com.example.orderprocessing.error.ValidationError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

enum class PaymentMethodType {
    CASH,
    CREDIT,
    PAYPAY,
    AMAZONPAY,
    GOOGLEPAY,
    APPLEPAY;

    companion object {
        fun fromString(value: String): Result<PaymentMethodType, List<ValidationError>> {
            return entries.firstOrNull { it.name == value.uppercase() }
                ?.let { Ok(it) }
                ?: Err(listOf(PaymentMethodValidationErrors.IllegalPaymentMethodType(value)))
        }
    }

    sealed interface PaymentMethodValidationErrors : ValidationError {
        data class IllegalPaymentMethodType(val value: String) : PaymentMethodValidationErrors {
            override val fieldName: String
                get() = PaymentMethodType::class.simpleName!!
            override val description: String
                get() = "支払い方法は${PaymentMethodType.entries}の中から選んでください。支払い方法: $value"
        }
    }
}