package com.example.orderprocessing.presentation.order

import java.math.BigDecimal

data class PaymentParam(
    val paymentMethod: String,
    val deliveryCharge: BigDecimal,
    val nonTaxedTotalPrice: BigDecimal,
    val tax: BigDecimal,
    val taxedTotalPrice: BigDecimal
) {
    companion object {
        fun new(
            paymentMethod: String,
            deliveryCharge: BigDecimal,
            nonTaxedTotalPrice: BigDecimal,
            tax: BigDecimal,
            taxedTotalPrice: BigDecimal
        ): PaymentParam {
            return PaymentParam(
                paymentMethod = paymentMethod,
                deliveryCharge = deliveryCharge,
                nonTaxedTotalPrice = nonTaxedTotalPrice,
                tax = tax,
                taxedTotalPrice = taxedTotalPrice
            )
        }
    }
}