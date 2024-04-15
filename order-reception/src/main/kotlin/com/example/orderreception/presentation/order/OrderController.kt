package com.example.orderreception.presentation.order

import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException
import com.example.orderreception.openapi.api.OrderApi
import com.example.orderreception.openapi.model.CreateOrderRequest
import com.example.orderreception.openapi.model.OrderCreationSuccessResponse
import com.example.orderreception.openapi.model.OrderCreationSuccessResponseData
import com.github.michaelbull.result.getOrElse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController : OrderApi {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun createOrder(createOrderRequest: CreateOrderRequest): ResponseEntity<OrderCreationSuccessResponse> {
        logger.info("Create order request: $createOrderRequest")

        val orderParam = OrderParam.fromOpenApi(order = createOrderRequest.order)
            .getOrElse { errors ->
                logger.warn("注文情報のバリデーションに失敗しました。: $${errors}")
                throw OrderReceptionIllegalArgumentException(validationErrors = errors)
            }

        logger.info("Order created successfully: $createOrderRequest")
        return ResponseEntity(
            // TODO: Replace with actual order ID
            OrderCreationSuccessResponse("SUCCESS", OrderCreationSuccessResponseData("11111")),
            HttpStatus.CREATED
        )
    }
}