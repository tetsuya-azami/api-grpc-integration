package com.example.orderprocessing.usecase.command

import com.example.grpcinterface.proto.OrderOuterClass.OrderCreationRequest
import com.example.orderprocessing.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class CreateOrder(private val orderRepository: OrderRepository) {
    fun execute(request: OrderCreationRequest): String {
        orderRepository.createOrder()
        return ""
    }
}