package com.example.orderreception.presentation.order

import com.example.orderreception.domain.model.order.DeliveryType
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
    val itemParams: List<ItemParam>,
    val chainId: Long,
    val shopId: Long,
    val deliveryType: DeliveryType,
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
            val deliveryType = DeliveryType.fromString(order.delivery.type?.name)
                .getOrElse { errors ->
                    validationErrors.addAll(errors)
                    null
                }
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

            return if (validationErrors.isNotEmpty() || paymentParam == null || deliveryType == null || time == null)
                Err(validationErrors)
            else Ok(
                OrderParam(
                    itemParams = itemParams,
                    chainId = order.chain.id!!,
                    shopId = order.shop.id!!,
                    deliveryType = deliveryType,
                    userId = order.user.id!!,
                    paymentParam = paymentParam,
                    time = time
                )
            )
        }
    }
}
