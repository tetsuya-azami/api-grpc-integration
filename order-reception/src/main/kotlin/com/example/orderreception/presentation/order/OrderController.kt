package com.example.orderreception.presentation.order

import com.example.orderreception.openapi.api.OrderApi
import com.example.orderreception.openapi.model.CreateOrderRequest
import com.example.orderreception.openapi.model.OrderCreationSuccessResponse
import com.example.orderreception.openapi.model.OrderCreationSuccessResponseData
import com.example.orderreception.usecase.RegisterOrder
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class OrderController(
    private val registerOrder: RegisterOrder
) : OrderApi {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun createOrder(createOrderRequest: CreateOrderRequest): ResponseEntity<OrderCreationSuccessResponse> {
        logger.info("Create order request: $createOrderRequest")
        val order = createOrderRequest.order

        val orderItemParams = order.items.map { item ->
            OrderItemParam.new(
                itemId = item.id,
                price = item.price?.let { BigDecimal(it) },
                attributes = item.attributes?.map { attribute ->
                    AttributeParam.new(attributeId = attribute.id)
                },
                quantity = item.quantity,
            )
        }

        val deliveryParam = DeliveryParam.new(
            deliveryType = order.delivery.type?.name,
            addressId = order.delivery.addressId,
        )

        val paymentParam = PaymentParam.new(
            paymentMethodType = order.payment.paymentMethod?.name,
            deliveryCharge = order.payment.deliveryCharge?.let { BigDecimal(it) },
            nonTaxedTotalPrice = order.payment.nonTaxedTotalPrice?.let { BigDecimal.valueOf(it) },
            tax = order.payment.tax?.let { BigDecimal.valueOf(it) },
            taxedTotalPrice = order.payment.taxedTotalPrice?.let { BigDecimal.valueOf(it) }
        )

        val orderParam =
            OrderParam.new(
                orderItemParams = orderItemParams,
                chainId = order.chain.id,
                shopId = order.shop.id,
                deliveryParam = deliveryParam,
                userId = order.user.id,
                paymentParam = paymentParam,
                timeString = order.time,
            )

        val registeredOrderId = registerOrder.execute(orderParam)

        logger.info("Order created successfully: $registeredOrderId")
        return ResponseEntity(
            OrderCreationSuccessResponse("SUCCESS", OrderCreationSuccessResponseData(registeredOrderId)),
            HttpStatus.CREATED
        )
    }
}
