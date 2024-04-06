package com.example.orderprocessing.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderprocessing.domain.model.PaymentMethodType
import java.math.BigDecimal

data class PaymentParam(
    val paymentMethod: PaymentMethodType,
    val deliveryCharge: BigDecimal,
    val nonTaxedTotalPrice: BigDecimal,
    val tax: BigDecimal,
    val taxedTotalPrice: BigDecimal
) {
    companion object {
        fun fromProto(paymentProto: OrderOuterClass.Payment): PaymentParam {
            return PaymentParam(
                paymentMethod = PaymentMethodType.fromString(paymentProto.paymentMethod.name),
                deliveryCharge = BigDecimal.valueOf(paymentProto.deliveryCharge),
                nonTaxedTotalPrice = BigDecimal.valueOf(paymentProto.nonTaxedTotalPrice),
                tax = BigDecimal.valueOf(paymentProto.tax),
                taxedTotalPrice = BigDecimal.valueOf(paymentProto.taxedTotalPrice)
            )
        }
    }
}