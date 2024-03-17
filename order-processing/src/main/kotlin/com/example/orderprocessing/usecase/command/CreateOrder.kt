package com.example.orderprocessing.usecase.command

import com.example.orderprocessing.model.order.Order
import com.example.orderprocessing.model.order.OrderId
import com.example.orderprocessing.repository.order.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CreateOrder(private val orderRepository: OrderRepository) {
    @Transactional
    fun execute(order: Order): OrderId {
        val now = LocalDateTime.now()
        orderRepository.createOrder(order, now)

        // TODO: 作成したorderのIDを返す
        return OrderId(1)
    }
}