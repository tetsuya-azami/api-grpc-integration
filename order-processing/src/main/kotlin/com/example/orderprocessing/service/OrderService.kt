package com.example.orderprocessing.service

import com.example.grpcinterface.proto.OrderOuterClass.OrderCreationRequest
import com.example.orderprocessing.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(private val orderRepository: OrderRepository) {
    fun createOrder(request: OrderCreationRequest): String {
        orderRepository.createOrder()
        return ""
    }
}