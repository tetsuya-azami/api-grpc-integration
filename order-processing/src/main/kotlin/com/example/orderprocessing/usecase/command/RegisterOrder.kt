package com.example.orderprocessing.usecase.command

import com.example.orderprocessing.domain.model.Order
import com.example.orderprocessing.domain.model.OrderId
import com.example.orderprocessing.error.ValidationError
import com.example.orderprocessing.error.exception.OrderProcessingIllegalArgumentException
import com.example.orderprocessing.infrastructure.repository.order.OrderRepository
import com.example.orderprocessing.presentation.order.OrderParam
import com.github.michaelbull.result.getOrElse
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
    fun execute(orderParam: OrderParam): OrderId {
        logger.info("注文登録処理開始")
        val now = LocalDateTime.now()

        val validationErrors = mutableListOf<ValidationError>()

        val order = Order.fromParam(orderParam)
            .getOrElse { errors ->
                validationErrors.addAll(errors)
                null
            }

        if (validationErrors.isNotEmpty() || order == null) {
            throw OrderProcessingIllegalArgumentException(validationErrors = validationErrors)
        }

        return orderRepository
            .registerOrder(order, now)
            .let {
                logger.info("注文登録処理終了")
                it
            }
    }
}