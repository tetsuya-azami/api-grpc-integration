package com.example.orderprocessing.usecase.command

import com.example.orderprocessing.model.order.Order
import com.example.orderprocessing.model.order.OrderId
import com.example.orderprocessing.repository.order.OrderRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class RegisterOrder(
    private val orderRepository: OrderRepository
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Transactional
    fun execute(order: Order): OrderId {
        logger.info("注文登録処理開始")

        val now = LocalDateTime.now()
        val registeredOrderId = orderRepository.registerOrder(order, now)

        logger.info("注文登録処理終了")
        return registeredOrderId
    }
}