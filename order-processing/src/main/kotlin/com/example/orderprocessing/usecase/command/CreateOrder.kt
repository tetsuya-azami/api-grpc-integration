package com.example.orderprocessing.usecase.command

import com.example.orderprocessing.model.order.Order
import com.example.orderprocessing.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class CreateOrder(private val orderRepository: OrderRepository) {
    fun execute(order: Order): String {
        orderRepository.createOrder()
        return ""
    }
}