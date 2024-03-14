package com.example.orderprocessing.repository

import com.example.orderprocessing.model.order.Order
import com.example.orderprocessing.repository.entity.generated.Orders
import com.example.orderprocessing.repository.mapper.generated.OrdersMapper
import com.example.orderprocessing.repository.mapper.generated.insert
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class OrderRepository(private val ordersMapper: OrdersMapper) {
    fun createOrder(order: Order) {
        val now = LocalDateTime.now()
        val row = Orders()
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
}