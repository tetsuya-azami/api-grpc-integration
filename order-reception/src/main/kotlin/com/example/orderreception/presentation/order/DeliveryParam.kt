package com.example.orderreception.presentation.order

import com.example.orderreception.error.ValidationError
import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException

data class DeliveryParam private constructor(
    val deliveryType: String,
    val addressId: Long
) {
    companion object {
        fun new(
            deliveryType: String?,
            addressId: Long?
        ): DeliveryParam {
            val validationErrors = mutableListOf<ValidationError>()
            if (deliveryType == null) {
                validationErrors.add(ValidationError(field = "delivery", message = "配達種別がありません。"))
            }

            if (addressId == null) {
                validationErrors.add(ValidationError(field = "delivery", message = "配達先住所情報がありません。"))
            }

            if (validationErrors.isNotEmpty()) {
                throw OrderReceptionIllegalArgumentException(validationErrors = validationErrors)
            }

            return DeliveryParam(
                deliveryType = deliveryType!!,
                addressId = addressId!!
            )
        }
    }
}