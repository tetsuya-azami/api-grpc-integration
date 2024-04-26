package com.example.orderprocessing.domain.model

import com.example.orderprocessing.error.ValidationError
import com.example.orderprocessing.presentation.order.PaymentParam
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.getOrElse
import java.math.BigDecimal
import java.math.RoundingMode

data class Payment private constructor(
    val paymentMethodType: PaymentMethodType,
    val deliveryCharge: BigDecimal,
    val nonTaxedTotalPrice: BigDecimal,
    val tax: BigDecimal,
    val taxedTotalPrice: BigDecimal
) {
    companion object {
        private val MINIMUM_TAXED_TOTAL_PRICE = BigDecimal.valueOf(0)
        private val MAXIMUM_TAXED_TOTAL_PRICE = BigDecimal.valueOf(9999999999)
        private val TAX_RATE = BigDecimal.valueOf(0.1)

        fun fromParam(paymentParam: PaymentParam): Result<Payment, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()

            // 税込金額上限チェック
            if (paymentParam.taxedTotalPrice < MINIMUM_TAXED_TOTAL_PRICE || MAXIMUM_TAXED_TOTAL_PRICE < paymentParam.taxedTotalPrice) {
                validationErrors.add(PaymentValidationError.IllegalTaxedTotalPrice)
            }
            // 消費税額整合性チェック
            if (((paymentParam.nonTaxedTotalPrice + paymentParam.deliveryCharge) * TAX_RATE).setScale(
                    0,
                    RoundingMode.DOWN
                ) != paymentParam.tax
            ) {
                validationErrors.add(PaymentValidationError.IllegalTax(paymentParam))
            }
            // 税込合計金額整合性チェック
            if (paymentParam.nonTaxedTotalPrice + paymentParam.deliveryCharge + paymentParam.tax != paymentParam.taxedTotalPrice) {
                validationErrors.add(PaymentValidationError.MissMatchTaxedTotalPrice(paymentParam))
            }

            val paymentMethodType = PaymentMethodType.fromString(paymentParam.paymentMethod).getOrElse {
                validationErrors.addAll(it)
                null
            }

            return if (validationErrors.isNotEmpty() || paymentMethodType == null)
                Err(validationErrors)
            else
                Ok(
                    Payment(
                        paymentMethodType = paymentMethodType,
                        deliveryCharge = paymentParam.deliveryCharge,
                        nonTaxedTotalPrice = paymentParam.nonTaxedTotalPrice,
                        tax = paymentParam.tax,
                        taxedTotalPrice = paymentParam.taxedTotalPrice
                    )
                )
        }
    }

    sealed interface PaymentValidationError : ValidationError {
        data object IllegalTaxedTotalPrice : PaymentValidationError {
            override val fieldName: String
                get() = Payment::taxedTotalPrice.name
            override val description: String
                get() = "税抜き合計金額は${MINIMUM_TAXED_TOTAL_PRICE}から${MAXIMUM_TAXED_TOTAL_PRICE}の間でなければなりません。"
        }

        data class IllegalTax(val paymentParam: PaymentParam) : PaymentValidationError {
            override val fieldName: String
                get() = Payment::tax.name
            override val description: String
                get() = "消費税額が不整合です。税抜き合計金額: ${paymentParam.nonTaxedTotalPrice}, 消費税: ${paymentParam.tax}"
        }

        data class MissMatchTaxedTotalPrice(val paymentParam: PaymentParam) : PaymentValidationError {
            override val fieldName: String
                get() = Payment::class.simpleName!!
            override val description: String
                get() = "税込合計価格が不整合です。税抜き合計金額: ${paymentParam.nonTaxedTotalPrice}, 消費税: ${paymentParam.tax}, 税込合計金額: ${paymentParam.taxedTotalPrice}"
        }
    }
}
