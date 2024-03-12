package com.example.orderprocessing.model.order

data class Payment(
    private val paymentMethodType: PaymentMethodType,
    private val deliveryCharge: Long,
    private val nonTaxedTotalPrice: Long,
    private val tax: Long,
    private val taxedTotalPrice: Long
) {
    // TODO: 金額の相関チェック
}
