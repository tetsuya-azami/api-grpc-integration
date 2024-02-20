package com.example.merchantreception.order

import com.example.merchantreception.api.OrdersApi
import com.example.merchantreception.model.CreateOrderRequest
import com.example.merchantreception.model.SuccessResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class OrdersController : OrdersApi {

    override fun createOrder(createOrderRequest: CreateOrderRequest): ResponseEntity<SuccessResponse> {
        println(createOrderRequest)
        return ResponseEntity(SuccessResponse("SUCCESS"), HttpStatus.OK)
    }

}
