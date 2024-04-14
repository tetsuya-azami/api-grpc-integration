package com.example.orderreception.model

import com.example.orderreception.error.ValidationError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

enum class DeliveryType {
    IMMEDIATE,
    SPECIFIED;

    companion object {
        fun fromString(value: String?): Result<DeliveryType, List<ValidationError>> {
            return entries.firstOrNull { it.name == value }
                ?.let { Ok(it) }
                ?: Err(listOf(ValidationError(message = "配達種別は${entries}の中から選んでください。配達種別: $value")))
        }
    }
}