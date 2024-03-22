package com.example.orderprocessing.repository.order

import com.example.orderprocessing.model.order.Order
import com.example.orderprocessing.model.order.OrderId
import com.example.orderprocessing.model.order.OrderItem
import com.example.orderprocessing.model.order.OrderItems
import com.example.orderprocessing.repository.entity.generated.OrderItemsBase
import com.example.orderprocessing.repository.entity.generated.OrdersBase
import com.example.orderprocessing.repository.mapper.generated.OrderItemsBaseMapper
import com.example.orderprocessing.repository.mapper.generated.OrdersBaseMapper
import com.example.orderprocessing.repository.mapper.generated.insert
import com.example.orderprocessing.repository.mapper.generated.insertMultiple
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class OrderRepository(private val ordersMapper: OrdersBaseMapper, private val orderItemsMapper: OrderItemsBaseMapper) {
    fun registerOrder(order: Order, now: LocalDateTime): OrderId {
        register(order, now)
        registerOrderItems(order.orderId, order.orderItems, now)
        
        return order.orderId
    }

    private fun register(order: Order, now: LocalDateTime) {
        val row = OrdersBase()
        row.orderId = order.orderId.value
        row.chainId = order.chainId
        row.shopId = order.shopId
        row.userId = order.user.userId
        row.paymentMethod = order.payment.paymentMethodType.name
        row.deliveryAddressId = order.delivery.addressId
        row.deliveryType = order.delivery.type.name
        row.deliveryCharge = order.payment.deliveryCharge
        row.nonTaxedTotalPrice = order.payment.nonTaxedTotalPrice
        row.tax = order.payment.tax
        row.taxedTotalPrice = order.payment.taxedTotalPrice
        row.time = order.time
        row.createdAt = now
        row.updatedAt = now

        ordersMapper.insert(row)
    }

    fun registerOrderItems(orderId: OrderId, orderItems: OrderItems, now: LocalDateTime) {
        val orderItemBaseList = orderItems.value.map { createOrderItemsBases(orderId, it, now) }

        orderItemsMapper.insertMultiple(orderItemBaseList)
    }

    private fun createOrderItemsBases(orderId: OrderId, orderItem: OrderItem, now: LocalDateTime): OrderItemsBase {
        val row = OrderItemsBase()
        row.orderId = orderId.value
        row.itemId = orderItem.itemId
        row.quantity = orderItem.quantity
        row.createdAt = now
        row.updatedAt = now

        return row
    }
}