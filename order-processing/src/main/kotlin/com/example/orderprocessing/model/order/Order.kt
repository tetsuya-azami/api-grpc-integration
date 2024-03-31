package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderprocessing.error.ValidationError
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * 注文ルートエンティティ
 */
class Order private constructor(
    val orderId: OrderId,
    val orderItems: OrderItems,
    val chainId: Long,
    val shopId: Long,
    val delivery: Delivery,
    val user: User,
    val payment: Payment,
    val time: LocalDateTime
) {

    companion object {
        fun fromOrderCreationRequest(request: OrderOuterClass.OrderCreationRequest): OrderValidationResult {
            val validationErrors = mutableListOf<ValidationError>()
            val order = request.order
            var orderItems: OrderItems? = null
            when (val orderItemsValidationResult = OrderItems.fromOrderCreationRequest(order.itemsList)) {
                is OrderItems.OrderItemsValidationResult.Success -> {
                    orderItems = orderItemsValidationResult.orderItems
                }

                is OrderItems.OrderItemsValidationResult.Failure -> {
                    validationErrors.addAll(orderItemsValidationResult.validationErrors)
                }
            }
            val delivery = Delivery.fromOrderCreationRequest(order)
            val user = User.fromOrderCreationRequest(order)
            var payment: Payment? = null
            when (val paymentValidationResult = Payment.fromOrderCreationRequest(order)) {
                is Payment.PaymentValidationResult.Success -> {
                    payment = paymentValidationResult.payment
                }

                is Payment.PaymentValidationResult.Failure -> {
                    validationErrors.addAll(paymentValidationResult.validationErrors)
                }
            }

            if (validationErrors.isNotEmpty() || orderItems == null || payment == null) {
                return OrderValidationResult.Failure(validationErrors)
            }

            return OrderValidationResult.Success(
                Order(
                    orderId = OrderId.new(),
                    orderItems = orderItems,
                    chainId = order.chain.id,
                    shopId = order.shop.id,
                    delivery = delivery,
                    user = user,
                    payment = payment,
                    time = LocalDateTime.ofEpochSecond(
                        order.time.seconds,
                        order.time.nanos,
                        ZoneOffset.of("+09:00")
                    )
                )
            )
        }
    }

    sealed interface OrderValidationResult {
        data class Success(val order: Order) : OrderValidationResult
        data class Failure(val validationErrors: List<ValidationError>) : OrderValidationResult
    }

    sealed interface OrderValidationErrors : ValidationError {
        data object IllegalTime : OrderValidationErrors {
            override val message: String
                get() = ""

        }
    }
}