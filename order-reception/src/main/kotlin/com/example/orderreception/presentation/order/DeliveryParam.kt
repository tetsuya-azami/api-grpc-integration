package com.example.orderreception.presentation.order

import com.example.orderreception.domain.model.order.DeliveryType
import com.example.orderreception.error.ValidationError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.getOrElse

data class DeliveryParam(
    val deliveryType: DeliveryType,
    val addressId: Long
) {
    companion object {
        fun fromOpenApi(delivery: com.example.orderreception.openapi.model.Delivery): Result<DeliveryParam, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()
            val deliveryType = DeliveryType.fromString(delivery.type?.name)
                .getOrElse { errors ->
                    validationErrors.addAll(errors)
                    null
                }

            val addressId = delivery.addressId ?: let {
                validationErrors.add(ValidationError(field = "delivery", message = "配達先住所情報がありません。"))
                null
            }

            return if (validationErrors.isNotEmpty()) Err(validationErrors)
            else Ok(
                DeliveryParam(
                    deliveryType = deliveryType!!,
                    addressId = addressId!!
                )
            )
        }
    }
}