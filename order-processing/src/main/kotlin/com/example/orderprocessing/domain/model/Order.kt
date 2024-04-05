package com.example.orderprocessing.domain.model

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderprocessing.error.ValidationError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.getOrElse
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
        fun fromOrderCreationRequest(request: OrderOuterClass.OrderCreationRequest): Result<Order, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()

            val orderProto = request.order
            val orderItems = OrderItems.fromOrderCreationRequest(orderProto.itemsList)
                .getOrElse {
                    validationErrors.addAll(it)
                    null
                }

            val delivery = Delivery.fromOrderCreationRequest(orderProto)
            val user = User.fromOrderCreationRequest(orderProto)
            val blackLevel = BlackLevel.fromString(orderProto.user.blackLevel.name)
            if (!isBlackLevelCanBeOrdered(blackLevel)) {
                validationErrors.add(OrderValidationErrors.IllegalOrderByBlackUser(blackLevel))
            }

            val payment = Payment.fromOrderCreationRequest(orderProto.payment)
                .getOrElse { errors ->
                    validationErrors.addAll(errors)
                    null
                }

            // 金額整合性チェック
            if (orderItems?.nonTaxedTotalPrice() != orderProto.payment.nonTaxedTotalPrice) {
                validationErrors.add(
                    OrderValidationErrors.IllegalNonTaxedTotalPrice(
                        orderItems,
                        payment
                    )
                )
            }

            if (validationErrors.isNotEmpty() || orderItems == null || payment == null) {
                return Err(validationErrors)
            }

            return Ok(
                Order(
                    orderId = OrderId.new(),
                    orderItems = orderItems,
                    chainId = orderProto.chain.id,
                    shopId = orderProto.shop.id,
                    delivery = delivery,
                    user = user,
                    payment = payment,
                    blackLevel = blackLevel,
                    time = LocalDateTime.ofEpochSecond(
                        orderProto.time.seconds,
                        orderProto.time.nanos,
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