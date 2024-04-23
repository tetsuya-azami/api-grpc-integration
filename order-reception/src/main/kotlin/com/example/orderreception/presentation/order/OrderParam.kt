package com.example.orderreception.presentation.order

import com.example.orderreception.error.ValidationError
import com.example.orderreception.openapi.model.Order
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.getOrElse
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class OrderParam(
    val orderItemParams: List<OrderItemParam>,
    val chainId: Long,
    val shopId: Long,
    val deliveryParam: DeliveryParam,
    val userId: Long,
    val paymentParam: PaymentParam,
    val time: LocalDateTime
) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)

        fun fromOpenApi(order: Order): Result<OrderParam, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()

            if (order.items.isEmpty()) validationErrors.add(ValidationError(message = "注文商品情報がありません。"))
            if (order.chain.id == null) validationErrors.add(ValidationError(message = "チェーン情報がありません。"))
            if (order.shop.id == null) validationErrors.add(ValidationError(message = "店舗情報がありません。"))
            val deliveryParam = DeliveryParam.fromOpenApi(order.delivery)
                .getOrElse { errors ->
                    validationErrors.addAll(errors)
                    null
                }
            if (order.user.id == null) validationErrors.add(ValidationError(message = "ユーザ情報がありません。"))

            val orderItemParams =
                order.items
                    .map { OrderItemParam.fromOpenApi(it) }
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

            val time = runCatching {
                LocalDateTime.parse(
                    order.time,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+09:00")
                )
            }.fold(
                onSuccess = { it },
                onFailure = {
                    logger.info(it.message, it.cause)
                    validationErrors.add(ValidationError(message = "注文日時は「yyyy-mm-ddTHH:mm:ss+09:00」の形式でなければなりません。注文日時: ${order.time}"))
                    null
                }
            )

            return if (validationErrors.isNotEmpty())
                Err(validationErrors)
            else Ok(
                OrderParam(
                    orderItemParams = orderItemParams,
                    chainId = order.chain.id!!,
                    shopId = order.shop.id!!,
                    deliveryParam = deliveryParam!!,
                    userId = order.user.id!!,
                    paymentParam = paymentParam!!,
                    time = time!!
                )
            )
        }
    }
}
