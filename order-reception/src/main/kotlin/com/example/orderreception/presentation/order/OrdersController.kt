package com.example.orderreception.presentation.order

import com.example.orderreception.api.OrderApi
import com.example.orderreception.error.exception.ValidationErrorException
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

        orderValidator.isOrderValid(createOrderRequest).let {
            if (it is OrderValidationResult.Invalid) {
                logger.warn("注文情報のバリデーションに失敗しました。: $it")
                throw ValidationErrorException(it.message)
            }
        }

        logger.info("Order created successfully: $createOrderRequest")
        return ResponseEntity(
            // TODO: Replace with actual order ID
            OrderCreationSuccessResponse("SUCCESS", OrderCreationSuccessResponseData(11111)),
            HttpStatus.CREATED
        )
    }
}
