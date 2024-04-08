package com.example.orderprocessing.domain.model

import com.example.orderprocessing.error.ValidationError
import com.example.orderprocessing.presentation.order.OrderParam
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.getOrElse
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
            val validationErrors = mutableListOf<ValidationError>()

            val orderItems = OrderItems.fromParam(orderParam.itemParams)
                .getOrElse {
                    validationErrors.addAll(it)
                    null
                }

            val delivery = Delivery.fromParam(orderParam.deliveryParam)
                .getOrElse {
                    validationErrors.addAll(it)
                    null
                }

            val user = User.fromParam(orderParam.userParam)

            val blackLevel = BlackLevel.fromString(orderParam.blackLevel).getOrElse {
                validationErrors.addAll(it)
                null
            }?.let {
                if (!isBlackLevelCanBeOrdered(it)) {
                    validationErrors.add(OrderValidationErrors.IllegalOrderByBlackUser(it))
                }
                it
            }

            val payment = Payment.fromParam(orderParam.paymentParam)
                .getOrElse { errors ->
                    validationErrors.addAll(errors)
                    null
                }

            // 金額整合性チェック
            if (orderItems?.nonTaxedTotalPrice() != orderParam.paymentParam.nonTaxedTotalPrice) {
                validationErrors.add(
                    OrderValidationErrors.IllegalNonTaxedTotalPrice(
                        orderItems,
                        payment
                    )
                )
            }

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