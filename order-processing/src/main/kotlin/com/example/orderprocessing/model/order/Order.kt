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
    val blackLevel: BlackLevel,
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
            val blackLevel = BlackLevel.fromString(order.user.blackLevel.name)
            if (!isBlackLevelCanBeOrdered(blackLevel)) {
                validationErrors.add(OrderValidationErrors.IllegalOrderByBlackUser(blackLevel))
            }

            var payment: Payment? = null
            when (val paymentValidationResult = Payment.fromOrderCreationRequest(order)) {
                is Payment.PaymentValidationResult.Success -> {
                    payment = paymentValidationResult.payment
                }

                is Payment.PaymentValidationResult.Failure -> {
                    validationErrors.addAll(paymentValidationResult.validationErrors)
                }
            }

            // 金額整合性チェック
            if (orderItems?.nonTaxedTotalPrice() != order.payment.nonTaxedTotalPrice) {
                validationErrors.add(
                    OrderValidationErrors.IllegalNonTaxedTotalPrice(
                        orderItems,
                        payment
                    )
                )
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
                    blackLevel = blackLevel,
                    time = LocalDateTime.ofEpochSecond(
                        order.time.seconds,
                        order.time.nanos,
                        ZoneOffset.of("+09:00")
                    )
                )
            )
        }

        private fun isBlackLevelCanBeOrdered(blackLevel: BlackLevel): Boolean {
            return when (blackLevel) {
                BlackLevel.LOW, BlackLevel.MIDDLE -> true
                BlackLevel.HIGH -> false
            }
        }
    }

    sealed interface OrderValidationResult {
        data class Success(val order: Order) : OrderValidationResult
        data class Failure(val validationErrors: List<ValidationError>) : OrderValidationResult
    }

    sealed interface OrderValidationErrors : ValidationError {
        data class IllegalOrderByBlackUser(val blackLevel: BlackLevel) : OrderValidationErrors {
            override val message: String
                get() = "不正な注文です。BlackLevel: ${blackLevel.name}"
        }

        // TODO: 引数のNull許容型をやめる
        data class IllegalNonTaxedTotalPrice(val orderItems: OrderItems?, val payment: Payment?) :
            OrderValidationErrors {
            override val message: String
                get() = "商品の税抜き合計金額が不整合です。購入商品一覧: ${orderItems?.value}, 税抜き合計金額: ${payment?.nonTaxedTotalPrice}"
        }
    }
}