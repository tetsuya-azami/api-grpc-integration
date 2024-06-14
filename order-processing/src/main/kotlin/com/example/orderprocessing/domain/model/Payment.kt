package com.example.orderprocessing.domain.model

import com.example.orderprocessing.error.ValidationError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
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

        fun new(
            paymentMethodType: PaymentMethodType,
            deliveryCharge: BigDecimal,
            nonTaxedTotalPrice: BigDecimal,
            tax: BigDecimal,
            taxedTotalPrice: BigDecimal
        ): Result<Payment, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()

            // 税込金額上限チェック
            if (taxedTotalPrice < MINIMUM_TAXED_TOTAL_PRICE || MAXIMUM_TAXED_TOTAL_PRICE < taxedTotalPrice) {
                validationErrors.add(
                    ValidationError(
                        fieldName = Payment::taxedTotalPrice.name,
                        description = "税抜き合計金額は${MINIMUM_TAXED_TOTAL_PRICE}から${MAXIMUM_TAXED_TOTAL_PRICE}の間でなければなりません。"
                    )
                )
            }
            // 消費税額整合性チェック
            if (((nonTaxedTotalPrice + deliveryCharge) * TAX_RATE).setScale(
                    0,
                    RoundingMode.DOWN
                ) != tax
            ) {
                validationErrors.add(
                    ValidationError(
                        fieldName = Payment::tax.name,
                        description = "消費税額が不整合です。税抜き合計金額: $nonTaxedTotalPrice, 消費税: $tax"
                    )
                )
            }
            // 税込合計金額整合性チェック
            if (nonTaxedTotalPrice + deliveryCharge + tax != taxedTotalPrice) {
                validationErrors.add(
                    ValidationError(
                        fieldName = "taxedTotalPrice",
                        description = "税込合計金額が不整合です。税抜き合計金額: $nonTaxedTotalPrice, 消費税: $tax, 税込合計金額: $taxedTotalPrice"
                    )
                )
            }

            return if (validationErrors.isNotEmpty())
                Err(validationErrors)
            else
                Ok(
                    Payment(
                        paymentMethodType = paymentMethodType,
                        deliveryCharge = deliveryCharge,
                        nonTaxedTotalPrice = nonTaxedTotalPrice,
                        tax = tax,
                        taxedTotalPrice = taxedTotalPrice
                    )
                )
        }
    }
}
