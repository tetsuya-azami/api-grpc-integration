package com.example.orderprocessing.presentation.order

import java.time.LocalDateTime

data class OrderParam private constructor(
    val orderItemParams: List<OrderItemParam>,
    val chainId: Long,
    val shopId: Long,
    val deliveryParam: DeliveryParam,
    val userParam: UserParam,
    val paymentParam: PaymentParam,
    val blackLevel: String,
    val time: LocalDateTime
) {
    companion object {
        fun new(
            orderItemParams: List<OrderItemParam>,
            chainId: Long,
            shopId: Long,
            deliveryParam: DeliveryParam,
            userParam: UserParam,
            paymentParam: PaymentParam,
            blackLevel: String,
            time: LocalDateTime,
        ): OrderParam {
            return OrderParam(
                orderItemParams = orderItemParams,
                chainId = chainId,
                shopId = shopId,
                deliveryParam = deliveryParam,
                userParam = userParam,
                paymentParam = paymentParam,
                blackLevel = blackLevel,
                time = time
            )
        }
    }
}
