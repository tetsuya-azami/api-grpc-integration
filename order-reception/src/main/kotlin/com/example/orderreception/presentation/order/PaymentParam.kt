package com.example.orderreception.presentation.order

import com.example.orderreception.domain.model.order.PaymentMethodType
import com.example.orderreception.error.ValidationError
import com.example.orderreception.openapi.model.Payment
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.getOrElse
import java.math.BigDecimal

data class PaymentParam(
    val paymentMethod: PaymentMethodType,
    val deliveryCharge: BigDecimal,
    val nonTaxedTotalPrice: BigDecimal,
    val tax: BigDecimal,
    val taxedTotalPrice: BigDecimal
) {
    companion object {
        fun fromOpenApi(payment: Payment): Result<PaymentParam, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()
            val paymentMethodType =
                PaymentMethodType.fromString(payment.paymentMethod?.name)
                    .getOrElse { errors ->
                        validationErrors.addAll(errors)
                        null
                    }
            if (payment.deliveryCharge == null) validationErrors.add(ValidationError(message = "配達料金がありません。"))
            if (payment.nonTaxedTotalPrice == null) validationErrors.add(ValidationError(message = "税抜き合計金額がありません。"))
            if (payment.tax == null) validationErrors.add(ValidationError(message = "消費税がありません。"))
            if (payment.taxedTotalPrice == null) validationErrors.add(ValidationError(message = "税込み合計金額がありません。"))

            return if (validationErrors.isNotEmpty()) Err(validationErrors)
            else Ok(
                PaymentParam(
                    paymentMethod = paymentMethodType!!,
                    deliveryCharge = BigDecimal.valueOf(payment.deliveryCharge!!),
                    nonTaxedTotalPrice = BigDecimal.valueOf(payment.nonTaxedTotalPrice!!),
                    tax = BigDecimal.valueOf(payment.tax!!),
                    taxedTotalPrice = BigDecimal.valueOf(payment.taxedTotalPrice!!)
                )
            )
        }
    }
}