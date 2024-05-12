package com.example.orderreception.helper

import com.example.orderreception.domain.model.order.Delivery.DeliveryType
import com.example.orderreception.domain.model.order.Payment.PaymentMethodType
import com.example.orderreception.presentation.order.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class OrderParamTestHelper {

    companion object {
        private val now = LocalDateTime.of(2000, 1, 2, 3, 4, 5)
        fun getTestInstance(): OrderParam {
            val orderItemParams = listOf(
                OrderItemParam.new(
                    itemId = 1,
                    price = BigDecimal.valueOf(100),
                    attributes = listOf(AttributeParam(1), AttributeParam(2)),
                    quantity = 1,
                ),
                OrderItemParam.new(
                    itemId = 2,
                    price = BigDecimal.valueOf(200),
                    attributes = listOf(AttributeParam(3), AttributeParam(4)),
                    quantity = 2,
                )
            )

            return OrderParam.new(
                orderItemParams = orderItemParams,
                chainId = 1,
                shopId = 1,
                deliveryParam = DeliveryParam.new(DeliveryType.IMMEDIATE.name, 1),
                userId = 1,
                paymentParam = PaymentParam.new(
                    paymentMethodType = PaymentMethodType.PAYPAY.name,
                    deliveryCharge = BigDecimal.valueOf(350),
                    nonTaxedTotalPrice = BigDecimal.valueOf(850),
                    tax = BigDecimal.valueOf(85),
                    taxedTotalPrice = BigDecimal.valueOf(935)
                ),
                timeString = now.format(DateTimeFormatter.ofPattern("yyyy-mm-ddTHH:mm:ss+09:00"))
            )
        }
    }
}