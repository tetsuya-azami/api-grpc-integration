package com.example.orderreception.presentation.order

import com.example.orderreception.api.OrderApi
import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException
import com.example.orderreception.model.CreateOrderRequest
import com.example.orderreception.model.OrderCreationSuccessResponse
import com.example.orderreception.model.OrderCreationSuccessResponseData
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    private val orderValidator: OrderValidator
) : OrderApi {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun createOrder(createOrderRequest: CreateOrderRequest): ResponseEntity<OrderCreationSuccessResponse> {
        logger.info("Create order request: $createOrderRequest")

        val validationErrors = orderValidator.isOrderValid(createOrderRequest)
        if (validationErrors.isNotEmpty()) {
            logger.warn("注文情報のバリデーションに失敗しました。: $${validationErrors}")
            throw OrderReceptionIllegalArgumentException(validationErrors = validationErrors)
        }

        logger.info("Order created successfully: $createOrderRequest")
        return ResponseEntity(
            // TODO: Replace with actual order ID
            OrderCreationSuccessResponse("SUCCESS", OrderCreationSuccessResponseData("11111")),
            HttpStatus.CREATED
        )
    }
}
