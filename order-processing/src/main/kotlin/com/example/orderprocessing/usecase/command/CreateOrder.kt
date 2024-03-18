package com.example.orderprocessing.usecase.command

import com.example.orderprocessing.model.order.Order
import com.example.orderprocessing.model.order.OrderId
import com.example.orderprocessing.repository.order.OrderItemRepository
import com.example.orderprocessing.repository.order.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CreateOrder(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository
) {
    @Transactional
    fun execute(order: Order): OrderId {
        val now = LocalDateTime.now()

        val orderId = orderRepository.createOrder(order, now)
        orderItemRepository.createOrderItem(orderId, order.orderItems, now)

        return orderId
    }
}