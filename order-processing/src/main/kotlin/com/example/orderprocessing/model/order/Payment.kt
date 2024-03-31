package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderprocessing.error.ValidationError
import java.math.BigDecimal

data class Payment private constructor(
    val paymentMethodType: PaymentMethodType,
    val deliveryCharge: Long,
    val nonTaxedTotalPrice: Long,
    val tax: Long,
    val taxedTotalPrice: Long
) {
    companion object {
        private const val MINIMUM_TAXED_TOTAL_PRICE = 0
        private const val MAXIMUM_TAXED_TOTAL_PRICE = 9999999999
        private val TAX_RATE = BigDecimal.valueOf(0.1)

        fun fromOrderCreationRequest(order: OrderOuterClass.Order): PaymentValidationResult {
            val payment = order.payment
            val validationErrors = mutableListOf<ValidationError>()

            // 税込金額上限チェック
            if (payment.taxedTotalPrice < MINIMUM_TAXED_TOTAL_PRICE || MAXIMUM_TAXED_TOTAL_PRICE < payment.taxedTotalPrice) {
                validationErrors.add(PaymentValidationError.IllegalTaxedTotalPrice)
            }
            // 消費税額整合性チェック
            if (BigDecimal.valueOf(payment.nonTaxedTotalPrice) * TAX_RATE != BigDecimal.valueOf(payment.tax)) {
                validationErrors.add(PaymentValidationError.IllegalTax(payment))
            }
            // 税込合計金額整合性チェック
            if (payment.nonTaxedTotalPrice + payment.tax != payment.taxedTotalPrice) {
                validationErrors.add(PaymentValidationError.MissMatchTaxedTotalPrice(payment))
            }

            if (validationErrors.isNotEmpty()) return PaymentValidationResult.Failure(validationErrors)

            return PaymentValidationResult.Success(
                Payment(
                    paymentMethodType = PaymentMethodType.fromString(payment.paymentMethod.name),
                    deliveryCharge = payment.deliveryCharge,
                    nonTaxedTotalPrice = payment.nonTaxedTotalPrice,
                    tax = payment.tax,
                    taxedTotalPrice = payment.taxedTotalPrice
                )
            )
        }
    }

    sealed interface PaymentValidationResult {
        data class Success(val payment: Payment) : PaymentValidationResult
        data class Failure(val validationErrors: List<ValidationError>) : PaymentValidationResult
    }

    sealed interface PaymentValidationError : ValidationError {
        data object IllegalTaxedTotalPrice : PaymentValidationError {
            override val message: String
                get() = "税抜き合計金額は${MINIMUM_TAXED_TOTAL_PRICE}から${MAXIMUM_TAXED_TOTAL_PRICE}の間でなければなりません。"
        }

        data class IllegalTax(val payment: OrderOuterClass.Payment) : PaymentValidationError {
            override val message: String
                get() = "消費税額が不整合です。税抜き合計金額: ${payment.nonTaxedTotalPrice}, 消費税: ${payment.tax}"
        }

        data class MissMatchTaxedTotalPrice(val payment: OrderOuterClass.Payment) : PaymentValidationError {
            override val message: String
                get() = "税込合計価格が不整合です。税抜き合計金額: ${payment.nonTaxedTotalPrice}, 消費税: ${payment.tax}, 税込合計金額: ${payment.taxedTotalPrice}"
        }
    }
}
