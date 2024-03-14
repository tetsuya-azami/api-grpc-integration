package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass

data class Payment(
    val paymentMethodType: PaymentMethodType,
    val deliveryCharge: Long,
    val nonTaxedTotalPrice: Long,
    val tax: Long,
    val taxedTotalPrice: Long
) {
    // TODO: 金額の相関チェック
    companion object {
        fun fromOrderCreationRequest(order: OrderOuterClass.Order): Payment {
            val payment = order.payment
            return Payment(
                paymentMethodType = PaymentMethodType.fromString(payment.paymentMethod.name),
                deliveryCharge = payment.deliveryCharge,
                nonTaxedTotalPrice = payment.nonTaxedTotalPrice,
                tax = payment.tax,
                taxedTotalPrice = payment.taxedTotalPrice
            )
        }
    }
}
