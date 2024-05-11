package com.example.orderreception.domain.model.order

import com.example.orderreception.error.ValidationError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import java.math.BigDecimal

class Payment private constructor(
    val paymentMethodType: PaymentMethodType,
    val deliveryCharge: BigDecimal,
    val nonTaxedTotalPrice: BigDecimal,
    val tax: BigDecimal,
    val taxedTotalPrice: BigDecimal
) {
    companion object {
        fun new(
            paymentMethodType: PaymentMethodType,
            deliveryCharge: BigDecimal,
            nonTaxedTotalPrice: BigDecimal,
            tax: BigDecimal,
            taxedTotalPrice: BigDecimal
        ): Payment {
            return Payment(
                paymentMethodType = paymentMethodType,
                deliveryCharge = deliveryCharge,
                nonTaxedTotalPrice = nonTaxedTotalPrice,
                tax = tax,
                taxedTotalPrice = taxedTotalPrice
            )
        }
    }

    enum class PaymentMethodType {
        CASH,
        CREDIT,
        PAYPAY,
        AMAZONPAY,
        GOOGLEPAY,
        APPLEPAY;

        companion object {
            fun fromString(value: String?): Result<PaymentMethodType, List<ValidationError>> {
                return PaymentMethodType.entries.firstOrNull { it.name == value?.uppercase() }
                    ?.let { Ok(it) }
                    ?: Err(
                        listOf(
                            ValidationError(
                                field = PaymentMethodType::class.simpleName!!,
                                message = "配達種別は${entries}の中から選んでください。配達種別: $value"
                            )
                        )
                    )
            }
        }
    }
}