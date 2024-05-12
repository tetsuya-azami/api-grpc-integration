package com.example.orderreception.helper

import com.example.orderreception.domain.model.order.*
import com.example.orderreception.domain.model.order.Delivery.DeliveryType
import com.example.orderreception.domain.model.order.Payment.PaymentMethodType
import java.math.BigDecimal
import java.time.LocalDateTime

class OrderTestHelper {
    companion object {
        fun getTestInstance(): Order {
            return Order.new(
                orderItem = listOf(
                    OrderItem.reconstruct(
                        itemId = 1,
                        name = "item1",
                        price = BigDecimal.valueOf(100),
                        attributes = listOf(
                            Attribute.reconstruct(id = 1, name = "属性名1", value = "属性値2"),
                            Attribute.reconstruct(id = 2, name = "属性名2", value = "属性値2")
                        ),
                        quantity = 1,
                    ),
                    OrderItem.reconstruct(
                        itemId = 2,
                        name = "item2",
                        price = BigDecimal.valueOf(200),
                        attributes = listOf(
                            Attribute.reconstruct(id = 3, name = "属性名3", value = "属性値3"),
                            Attribute.reconstruct(id = 4, name = "属性名4", value = "属性値4")
                        ),
                        quantity = 2,
                    )
                ),
                chainId = 1,
                shopId = 1,
                delivery = Delivery.new(DeliveryType.IMMEDIATE, 1),
                userId = 1,
                payment = Payment.new(
                    paymentMethodType = PaymentMethodType.PAYPAY,
                    deliveryCharge = BigDecimal.valueOf(350),
                    nonTaxedTotalPrice = BigDecimal.valueOf(850),
                    tax = BigDecimal.valueOf(85),
                    taxedTotalPrice = BigDecimal.valueOf(935)
                ),
                time = LocalDateTime.of(2000, 1, 2, 3, 4, 5)
            )
        }
    }
}