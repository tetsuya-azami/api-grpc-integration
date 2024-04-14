package com.example.orderreception.presentation.order

import com.example.orderreception.error.ValidationError
import com.example.orderreception.openapi.model.Order
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.getOrElse

data class OrderParam(
    val itemParams: List<ItemParam>,
    val chainId: Long,
    val shopId: Long,
    val deliveryType: String,
    val userId: Long,
    val paymentParam: PaymentParam,
    val time: String
) {
    companion object {
        fun fromOpenApi(order: Order): Result<OrderParam, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()

            if (order.items.isEmpty()) validationErrors.add(ValidationError(message = "注文商品情報がありません。"))
            if (order.chain.id == null) validationErrors.add(ValidationError(message = "チェーン情報がありません。"))
            if (order.shop.id == null) validationErrors.add(ValidationError(message = "店舗情報がありません。"))
            if (order.delivery.type == null) validationErrors.add(ValidationError(message = "配達情報がありません。"))
            if (order.user.id == null) validationErrors.add(ValidationError(message = "ユーザ情報がありません。"))

            val itemParams =
                order.items
                    .map { ItemParam.fromOpenApi(it) }
                    .mapNotNull { result ->
                        result.getOrElse { errors ->
                            validationErrors.addAll(errors)
                            null
                        }
                    }

            val paymentParam = PaymentParam.fromOpenApi(order.payment)
                .getOrElse {
                    validationErrors.addAll(it)
                    null
                }

            return if (validationErrors.isNotEmpty() || paymentParam == null) Err(validationErrors)
            else Ok(
                OrderParam(
                    itemParams = itemParams,
                    chainId = order.chain.id!!,
                    shopId = order.shop.id!!,
                    deliveryType = order.delivery.type!!.name,
                    userId = order.user.id!!,
                    paymentParam = paymentParam,
                    time = order.time
                )
            )
        }
    }
}
