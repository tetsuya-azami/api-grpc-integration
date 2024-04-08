package com.example.orderprocessing.domain.model

import com.example.orderprocessing.error.ValidationError
import com.example.orderprocessing.presentation.order.DeliveryParam
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.getOrElse

data class Delivery private constructor(
    var type: DeliveryType,
    var addressId: Long
) {
    companion object {
        fun fromParam(deliveryParam: DeliveryParam): Result<Delivery, List<ValidationError>> {
            val deliveryTypeValidationResult = DeliveryType.fromString(deliveryParam.deliveryType)
            val validationErrors = mutableListOf<ValidationError>()
            
            val deliveryType = deliveryTypeValidationResult.getOrElse { errors ->
                validationErrors.addAll(errors)
                null
            }

            return if (validationErrors.isNotEmpty())
                Err(validationErrors)
            else
                Ok(
                    Delivery(
                        type = deliveryType!!,
                        addressId = deliveryParam.addressId
                    )
                )
        }
    }
}