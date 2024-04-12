package com.example.orderreception.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderreception.error.ValidationError
import com.example.orderreception.openapi.model.CreateOrderRequest
import com.example.orderreception.openapi.model.Delivery
import com.example.orderreception.openapi.model.Payment
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class OrderValidator {

    fun isOrderValid(createOrderRequest: CreateOrderRequest): List<ValidationError> {
        val validationErrors = mutableListOf<ValidationError>()
        val order = createOrderRequest.order
        validationErrors.addAll(getDeliveryValidationErrors(order.delivery))
        validationErrors.addAll(getPaymentValidationErrors(order.payment))

        try {
            LocalDateTime.parse(order.time, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+09:00"))
        } catch (e: Exception) {
            e.printStackTrace()
            validationErrors.add(OrderValidationError.IllegalOrderTime(order.time))
        }

        return validationErrors
    }

    private fun getDeliveryValidationErrors(delivery: Delivery): List<ValidationError> {
        val validationErrors = mutableListOf<ValidationError>()
        if (delivery.type == null) {
            validationErrors.add(OrderValidationError.IllegalDeliveryType(delivery.type))
        }

        return validationErrors
    }

    private fun getPaymentValidationErrors(payment: Payment): List<ValidationError> {
        val validationErrors = mutableListOf<ValidationError>()
        if (payment.paymentMethod == null) {
            validationErrors.add(OrderValidationError.IllegalPaymentType(payment.paymentMethod))
        }

        return validationErrors
    }

    sealed interface OrderValidationError : ValidationError {
        data class IllegalDeliveryType(val deliveryType: OrderOuterClass.Delivery.Type?) : OrderValidationError {
            override val message: String
                get() = "配送種別は${Delivery.Type.entries}のいずれかでなければなりません。配送種別: ${deliveryType?.name}"
        }

        data class IllegalPaymentType(val paymentType: OrderOuterClass.Payment.PaymentMethod?) : OrderValidationError {
            override val message: String
                get() = "支払い方法は${Payment.PaymentMethod.entries}のいずれかでなければなりません。支払い方法: ${paymentType?.name}"
        }

        data class IllegalOrderTime(val time: String) : OrderValidationError {
            override val message: String
                get() = "注文日時はyyyy-mm-ddTHH:mm:ss+09:00の形式でなければなりません。注文日時: $time"
        }
    }
}