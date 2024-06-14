package com.example.orderprocessing.domain.model

import com.example.orderprocessing.error.ValidationError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
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
        fun new(
            orderItems: OrderItems,
            chainId: Long,
            shopId: Long,
            delivery: Delivery,
            user: User,
            payment: Payment,
            blackLevel: BlackLevel,
            time: LocalDateTime
        ): Result<Order, List<ValidationError>> {
            // 金額整合性チェック
            val nonTaxedTotalPriceErrors = validateNonTaxedTotalPrice(orderItems, payment)
            val blackLevelErrors = validateBlackLevel(blackLevel)
            val validationErrors = nonTaxedTotalPriceErrors + blackLevelErrors

            return if (validationErrors.isNotEmpty())
                Err(nonTaxedTotalPriceErrors)
            else
                Ok(
                    Order(
                        orderId = OrderId.new(),
                        orderItems = orderItems,
                        chainId = chainId,
                        shopId = shopId,
                        delivery = delivery,
                        user = user,
                        payment = payment,
                        blackLevel = blackLevel,
                        time = time
                    )
                )
        }

        private fun validateNonTaxedTotalPrice(
            orderItems: OrderItems,
            payment: Payment
        ): List<ValidationError> {
            return if (orderItems.nonTaxedTotalPrice() != payment.nonTaxedTotalPrice) {
                listOf(
                    ValidationError(
                        fieldName = "nonTaxedTotalPrice",
                        description = "商品の税抜き合計金額が不整合です。購入商品一覧: ${orderItems.value}, 税抜き合計金額: ${payment.nonTaxedTotalPrice}"
                    )
                )
            } else {
                emptyList()
            }
        }

        private fun validateBlackLevel(blackLevel: BlackLevel): List<ValidationError> {
            return when (blackLevel) {
                BlackLevel.LOW, BlackLevel.MIDDLE -> emptyList()
                BlackLevel.HIGH -> listOf(
                    ValidationError(
                        fieldName = "blackLevel",
                        description = "不正なユーザのため注文を完了できませんでした。ブラックレベル: $blackLevel"
                    )
                )
            }
        }
    }
}