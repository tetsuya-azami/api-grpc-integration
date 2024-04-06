package com.example.orderprocessing.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderprocessing.domain.model.PaymentMethodType

data class PaymentParam(
    val paymentMethod: PaymentMethodType,
    val deliveryCharge: Long,
    val nonTaxedTotalPrice: Long,
    val tax: Long,
    val taxedTotalPrice: Long
) {
    companion object {
        fun fromProto(paymentProto: OrderOuterClass.Payment): PaymentParam {
            return PaymentParam(
                paymentMethod = PaymentMethodType.fromString(paymentProto.paymentMethod.name),
                deliveryCharge = paymentProto.deliveryCharge,
                nonTaxedTotalPrice = paymentProto.nonTaxedTotalPrice,
                tax = paymentProto.tax,
                taxedTotalPrice = paymentProto.taxedTotalPrice
            )
        }
    }
}