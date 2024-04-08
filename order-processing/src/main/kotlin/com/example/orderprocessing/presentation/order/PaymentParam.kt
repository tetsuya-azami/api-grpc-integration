package com.example.orderprocessing.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass
import java.math.BigDecimal

data class PaymentParam(
    val paymentMethod: String,
    val deliveryCharge: BigDecimal,
    val nonTaxedTotalPrice: BigDecimal,
    val tax: BigDecimal,
    val taxedTotalPrice: BigDecimal
) {
    companion object {
        fun fromProto(paymentProto: OrderOuterClass.Payment): PaymentParam {
            return PaymentParam(
                paymentMethod = paymentProto.paymentMethod.name,
                deliveryCharge = BigDecimal.valueOf(paymentProto.deliveryCharge),
                nonTaxedTotalPrice = BigDecimal.valueOf(paymentProto.nonTaxedTotalPrice),
                tax = BigDecimal.valueOf(paymentProto.tax),
                taxedTotalPrice = BigDecimal.valueOf(paymentProto.taxedTotalPrice)
            )
        }
    }
}