package com.example.orderreception.presentation.order

import com.example.orderreception.model.CreateOrderRequest
import com.example.orderreception.model.Delivery
import com.example.orderreception.model.Payment
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class OrderValidator {

    fun isOrderValid(createOrderRequest: CreateOrderRequest): OrderValidationResult {
        val order = createOrderRequest.order
        if (isDeliveryValid(order.delivery) is OrderValidationResult.Invalid) {
            return isDeliveryValid(order.delivery)
        }
        if (isPaymentValid(order.payment) is OrderValidationResult.Invalid) {
            return isPaymentValid(order.payment)
        }
        try {
            LocalDateTime.parse(order.time, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+09:00"))
        } catch (e: Exception) {
            e.printStackTrace()
            return OrderValidationResult.invalid("注文日時はyyyy-mm-ddTHH:mm:ss+09:00の形式でなければなりません。")
        }
        return OrderValidationResult.valid()
    }

    private fun isDeliveryValid(delivery: Delivery): OrderValidationResult {
        if (delivery.type == null) {
            return OrderValidationResult.invalid("配送種別は${Delivery.Type.entries}のいずれかでなければなりません。")
        }
        return OrderValidationResult.valid()
    }

    private fun isPaymentValid(payment: Payment): OrderValidationResult {
        if (payment.paymentMethod == null) {
            return OrderValidationResult.invalid("支払い方法は${Payment.PaymentMethod.entries}のいずれかでなければなりません。")
        }
        
        return OrderValidationResult.valid()
    }
}