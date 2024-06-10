package com.example.orderprocessing.domain.model

import com.example.orderprocessing.error.ValidationError
import com.example.orderprocessing.presentation.order.OrderParam
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.fold
import java.math.BigDecimal
import java.time.LocalDateTime

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
        fun fromParam(orderParam: OrderParam): Result<Order, List<ValidationError>> {
            val (orderItems, orderItemsValidationErrors) = OrderItems.fromParam(orderParam.orderItemParams).fold(
                success = { it to emptyList() },
                failure = { null to it }
            )

            val (delivery, deliveryValidationErrors) = Delivery.fromParam(orderParam.deliveryParam).fold(
                success = { it to emptyList() },
                failure = { null to it }
            )

            val user = User.fromParam(orderParam.userParam)

            val (blackLevel, blackLevelValidationErrors) = BlackLevel.fromString(orderParam.blackLevel).fold(
                success = {
                    if (!isBlackLevelCanBeOrdered(it)) {
                        null to listOf(OrderValidationErrors.IllegalOrderByBlackUser(it))
                    } else {
                        it to emptyList()
                    }
                },
                failure = { null to it }
            )

            val (payment, paymentValidationErrors) = Payment.fromParam(orderParam.paymentParam).fold(
                success = { it to emptyList() },
                failure = { null to it }
            )

            // 金額整合性チェック
            val nonTaxedTotalPriceErrors =
                if (orderItems?.nonTaxedTotalPrice() != orderParam.paymentParam.nonTaxedTotalPrice) {
                    listOf(
                        OrderValidationErrors.IllegalNonTaxedTotalPrice(
                            orderItems = orderItems,
                            nonTaxedTotalPrice = orderParam.paymentParam.nonTaxedTotalPrice
                        )
                    )
                } else {
                    emptyList()
                }

            val validationErrors =
                orderItemsValidationErrors + deliveryValidationErrors + blackLevelValidationErrors + paymentValidationErrors + nonTaxedTotalPriceErrors

            return if (validationErrors.isNotEmpty() || orderItems == null || payment == null || delivery == null || blackLevel == null)
                Err(validationErrors)
            else
                Ok(
                    Order(
                        orderId = OrderId.new(),
                        orderItems = orderItems,
                        chainId = orderParam.chainId,
                        shopId = orderParam.shopId,
                        delivery = delivery,
                        user = user,
                        payment = payment,
                        blackLevel = blackLevel,
                        time = orderParam.time
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

    sealed interface OrderValidationErrors : ValidationError {
        data class IllegalOrderByBlackUser(val blackLevel: BlackLevel) : OrderValidationErrors {
            override val fieldName: String
                get() = BlackLevel::class.simpleName!!
            override val description: String
                get() = "不正な注文です。BlackLevel: ${blackLevel.name}"
        }

        // TODO: 引数のNull許容型をやめる
        data class IllegalNonTaxedTotalPrice(val orderItems: OrderItems?, val nonTaxedTotalPrice: BigDecimal?) :
            OrderValidationErrors {
            override val fieldName: String
                get() = Payment::nonTaxedTotalPrice.name
            override val description: String
                get() = "商品の税抜き合計金額が不整合です。購入商品一覧: ${orderItems?.value}, 税抜き合計金額: $nonTaxedTotalPrice"
        }
    }
}