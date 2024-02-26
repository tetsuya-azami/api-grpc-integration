package com.example.merchantreception.presentation.order

import com.example.merchantreception.api.OrderApi
import com.example.merchantreception.error.exception.ValidationErrorException
import com.example.merchantreception.model.CreateOrderRequest
import com.example.merchantreception.model.SuccessResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController

class OrderController(
    private val orderValidator: OrderValidator
) : OrderApi {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun createOrder(createOrderRequest: CreateOrderRequest): ResponseEntity<SuccessResponse> {
        logger.info("Create order request: $createOrderRequest")

        orderValidator.isOrderValid(createOrderRequest).let {
            if (it is OrderValidationResult.Invalid) {
                logger.warn("注文情報のバリデーションに失敗しました。: $it")
                throw ValidationErrorException(it.message)
            }
        }

        logger.info("Order created successfully: $createOrderRequest")
        return ResponseEntity(SuccessResponse("SUCCESS"), HttpStatus.OK)
    }
}
