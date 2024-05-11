package com.example.orderreception.domain.model.order

import com.example.orderreception.error.ValidationError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

class Delivery private constructor(
    val deliveryType: DeliveryType,
    val addressId: Long
) {
    companion object {
        fun new(deliveryType: DeliveryType, addressId: Long): Delivery {
            return Delivery(
                deliveryType = deliveryType,
                addressId = addressId
            )
        }
    }

    enum class DeliveryType {
        IMMEDIATE,
        SPECIFIED;

        companion object {
            fun fromString(value: String?): Result<DeliveryType, List<ValidationError>> {
                return entries.firstOrNull { it.name == value?.uppercase() }
                    ?.let { Ok(it) }
                    ?: Err(
                        listOf(
                            ValidationError(
                                field = DeliveryType::class.simpleName!!,
                                message = "配達種別は${entries}の中から選んでください。配達種別: $value"
                            )
                        )
                    )
            }
        }
    }
}