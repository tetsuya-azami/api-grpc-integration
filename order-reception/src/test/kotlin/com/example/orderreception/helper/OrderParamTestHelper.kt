package com.example.orderreception.helper

import com.example.orderreception.domain.model.order.DeliveryType
import com.example.orderreception.domain.model.order.PaymentMethodType
import com.example.orderreception.presentation.order.*
import java.math.BigDecimal
import java.time.LocalDateTime

class OrderParamTestHelper {

    companion object {
        private val now = LocalDateTime.of(2000, 1, 2, 3, 4, 5)
        fun getTestInstance(): OrderParam {
            val orderItemParams = listOf(
                OrderItemParam(
                    itemId = 1,
                    price = BigDecimal.valueOf(100),
                    attributes = listOf(AttributeParam(1), AttributeParam(2)),
                    quantity = 1,
                ),
                OrderItemParam(
                    itemId = 2,
                    price = BigDecimal.valueOf(200),
                    attributes = listOf(AttributeParam(3), AttributeParam(4)),
                    quantity = 2,
                )
            )

            return OrderParam(
                orderItemParams = orderItemParams,
                chainId = 1,
                shopId = 1,
                deliveryParam = DeliveryParam(DeliveryType.IMMEDIATE, 1),
                userId = 1,
                paymentParam = PaymentParam(
                    paymentMethod = PaymentMethodType.PAYPAY,
                    deliveryCharge = BigDecimal.valueOf(350),
                    nonTaxedTotalPrice = BigDecimal.valueOf(850),
                    tax = BigDecimal.valueOf(85),
                    taxedTotalPrice = BigDecimal.valueOf(935)
                ),
                time = now,
            )
        }
    }
}