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
                deliveryCharge = BigDecimal.valueOf(paymentProto.deliveryCharge.units),
                nonTaxedTotalPrice = BigDecimal.valueOf(paymentProto.nonTaxedTotalPrice.units),
                tax = BigDecimal.valueOf(paymentProto.tax.units),
                taxedTotalPrice = BigDecimal.valueOf(paymentProto.taxedTotalPrice.units)
            )
        }
    }
}