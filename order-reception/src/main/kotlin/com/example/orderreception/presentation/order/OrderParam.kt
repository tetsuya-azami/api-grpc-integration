package com.example.orderreception.presentation.order

import com.example.orderreception.error.ValidationError
import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class OrderParam private constructor(
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
        fun new(
            orderItemParams: List<OrderItemParam>?,
            chainId: Long?,
            shopId: Long?,
            deliveryParam: DeliveryParam,
            userId: Long?,
            paymentParam: PaymentParam,
            timeString: String?
        ): OrderParam {
            val validationErrors = mutableListOf<ValidationError>()

            if (orderItemParams.isNullOrEmpty()) {
                validationErrors.add(
                    ValidationError(
                        field = "items",
                        message = "注文商品情報がありません。"
                    )
                )
            }

            if (chainId == null) {
                validationErrors.add(
                    ValidationError(
                        field = "chain",
                        message = "チェーン情報がありません。"
                    )
                )
            }

            if (shopId == null) {
                validationErrors.add(
                    ValidationError(
                        field = "shop",
                        message = "店舗情報がありません。"
                    )
                )
            }

            if (userId == null) {
                validationErrors.add(
                    ValidationError(
                        field = "user",
                        message = "ユーザ情報がありません。"
                    )
                )
            }

            val time = runCatching {
                LocalDateTime.parse(
                    timeString,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+09:00")
                )
            }.fold(
                onSuccess = { it },
                onFailure = {
                    logger.info(it.message, it.cause)
                    validationErrors.add(
                        ValidationError(
                            field = "time",
                            message = "注文日時は「yyyy-mm-ddTHH:mm:ss+09:00」の形式でなければなりません。注文日時: $timeString"
                        )
                    )
                    // 戻り値の型をLocalDateTimeにするために記述。TODO: もっといい方法がないか調査
                    LocalDateTime.now()
                }
            )

            if (validationErrors.isNotEmpty()) throw OrderReceptionIllegalArgumentException(validationErrors = validationErrors)

            return OrderParam(
                orderItemParams = orderItemParams!!,
                chainId = chainId!!,
                shopId = shopId!!,
                deliveryParam = deliveryParam,
                userId = userId!!,
                paymentParam = paymentParam,
                time = time!!
            )
        }
    }
}
