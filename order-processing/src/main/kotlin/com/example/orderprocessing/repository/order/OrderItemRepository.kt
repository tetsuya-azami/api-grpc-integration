package com.example.orderprocessing.repository.order

import com.example.orderprocessing.model.order.OrderId
import com.example.orderprocessing.model.order.OrderItem
import com.example.orderprocessing.model.order.OrderItems
import com.example.orderprocessing.repository.entity.generated.OrderItemsBase
import com.example.orderprocessing.repository.mapper.generated.OrderItemsBaseMapper
import com.example.orderprocessing.repository.mapper.generated.insertMultiple
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class OrderItemRepository(private val orderItemsMapper: OrderItemsBaseMapper) {
    fun registerOrderItems(orderId: OrderId, orderItems: OrderItems, now: LocalDateTime) {
        val orderItemBaseList = orderItems.value.map { createOrderItem(orderId, it, now) }

        orderItemsMapper.insertMultiple(orderItemBaseList)
    }

    private fun createOrderItem(orderId: OrderId, orderItem: OrderItem, now: LocalDateTime): OrderItemsBase {
        val row = OrderItemsBase()
        row.orderId = orderId.value
        row.itemId = orderItem.itemId
        row.quantity = orderItem.quantity
        row.createdAt = now
        row.updatedAt = now

        return row
    }
}