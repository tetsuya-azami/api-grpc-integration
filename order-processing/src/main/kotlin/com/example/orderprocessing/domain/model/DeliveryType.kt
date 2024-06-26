package com.example.orderprocessing.domain.model

import com.example.orderprocessing.error.ValidationError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

enum class DeliveryType {
    IMMEDIATE,
    SPECIFIED;

    companion object {
        fun fromString(value: String): Result<DeliveryType, List<ValidationError>> {
            return entries.firstOrNull { it.name == value.uppercase() }
                ?.let { Ok(it) }
                ?: Err(
                    listOf(
                        ValidationError(
                            fieldName = "deliveryType",
                            description = "配達種別は${entries}の中から選んでください。配達種別: $value"
                        )
                    )
                )
        }
    }
}