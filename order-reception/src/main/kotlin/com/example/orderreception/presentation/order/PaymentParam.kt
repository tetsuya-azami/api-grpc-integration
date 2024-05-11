package com.example.orderreception.presentation.order

import com.example.orderreception.error.ValidationError
import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException
import java.math.BigDecimal

/**
 * 支払い情報Param
 */
data class PaymentParam private constructor(
    val paymentMethodType: String,
    val deliveryCharge: BigDecimal,
    val nonTaxedTotalPrice: BigDecimal,
    val tax: BigDecimal,
    val taxedTotalPrice: BigDecimal
) {
    companion object {
        fun new(
            paymentMethodType: String?,
            deliveryCharge: BigDecimal?,
            nonTaxedTotalPrice: BigDecimal?,
            tax: BigDecimal?,
            taxedTotalPrice: BigDecimal?
        ): PaymentParam {
            val validationErrors = mutableListOf<ValidationError>()
            if (paymentMethodType == null) {
                validationErrors.add(
                    ValidationError(
                        field = "payment",
                        message = "支払い方法がありません。"
                    )
                )
            }
            if (deliveryCharge == null) {
                validationErrors.add(
                    ValidationError(
                        field = "payment",
                        message = "配達料金がありません。"
                    )
                )
            }
            if (nonTaxedTotalPrice == null) {
                validationErrors.add(
                    ValidationError(
                        field = "payment",
                        message = "税抜き合計金額がありません。"
                    )
                )
            }
            if (tax == null) {
                validationErrors.add(
                    ValidationError(
                        field = "payment",
                        message = "消費税がありません。"
                    )
                )
            }
            if (taxedTotalPrice == null) {
                validationErrors.add(
                    ValidationError(
                        field = "payment",
                        message = "税込み合計金額がありません。"
                    )
                )
            }

            if (validationErrors.isNotEmpty()) {
                throw OrderReceptionIllegalArgumentException(validationErrors = validationErrors)
            }

            return PaymentParam(
                paymentMethodType = paymentMethodType!!,
                deliveryCharge = deliveryCharge!!,
                nonTaxedTotalPrice = nonTaxedTotalPrice!!,
                tax = tax!!,
                taxedTotalPrice = taxedTotalPrice!!,
            )
        }
    }
}
