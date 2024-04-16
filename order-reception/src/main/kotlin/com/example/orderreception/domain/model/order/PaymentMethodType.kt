package com.example.orderreception.domain.model.order

import com.example.orderreception.error.ValidationError
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
        fun fromString(value: String?): Result<PaymentMethodType, List<ValidationError>> {
            return entries.firstOrNull { it.name == value?.uppercase() }
                ?.let { Ok(it) }
                ?: Err(listOf(ValidationError(message = "配達種別は${PaymentMethodType.entries}の中から選んでください。配達種別: $value")))
        }
    }
}