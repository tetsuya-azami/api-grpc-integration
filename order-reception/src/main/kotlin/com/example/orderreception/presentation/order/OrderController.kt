package com.example.orderreception.presentation.order

import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException
import com.example.orderreception.openapi.api.OrderApi
import com.example.orderreception.openapi.model.CreateOrderRequest
import com.example.orderreception.openapi.model.OrderCreationSuccessResponse
import com.example.orderreception.openapi.model.OrderCreationSuccessResponseData
import com.example.orderreception.usecase.RegisterOrder
import com.github.michaelbull.result.getOrElse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    private val registerOrder: RegisterOrder
) : OrderApi {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun createOrder(createOrderRequest: CreateOrderRequest): ResponseEntity<OrderCreationSuccessResponse> {
        logger.info("Create order request: $createOrderRequest")

        val orderParam = OrderParam.fromOpenApi(order = createOrderRequest.order)
            .getOrElse { errors ->
                logger.warn("注文情報のバリデーションに失敗しました。: $${errors}")
                throw OrderReceptionIllegalArgumentException(validationErrors = errors)
            }

        val registeredOrderId = registerOrder.execute(orderParam)

        logger.info("Order created successfully: $registeredOrderId")
        return ResponseEntity(
            OrderCreationSuccessResponse("SUCCESS", OrderCreationSuccessResponseData(registeredOrderId)),
            HttpStatus.CREATED
        )
    }
}
