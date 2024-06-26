package com.example.orderprocessing.infrastructure.repository.order

import com.example.orderprocessing.domain.model.Order
import com.example.orderprocessing.domain.model.OrderId
import com.example.orderprocessing.domain.model.OrderItems
import com.example.orderprocessing.infrastructure.entity.generated.OrderItemAttributesBase
import com.example.orderprocessing.infrastructure.entity.generated.OrderItemsBase
import com.example.orderprocessing.infrastructure.entity.generated.OrdersBase
import com.example.orderprocessing.infrastructure.mapper.generated.*
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class OrderRepository(
    private val ordersMapper: OrdersBaseMapper,
    private val orderItemsMapper: OrderItemsBaseMapper,
    private val orderItemAttributesMapper: OrderItemAttributesBaseMapper
) {
    fun registerOrder(order: Order, now: LocalDateTime): OrderId {
        register(order, now)
        registerOrderItems(order.orderId, order.orderItems, now)
        registerOrderItemAttributes(order, now)

        return order.orderId
    }

    private fun register(order: Order, now: LocalDateTime) {
        val row = OrdersBase(
            orderId = order.orderId.value,
            chainId = order.chainId,
            shopId = order.shopId,
            userId = order.user.userId,
            paymentMethod = order.payment.paymentMethodType.name,
            deliveryAddressId = order.delivery.addressId,
            deliveryType = order.delivery.type.name,
            deliveryCharge = order.payment.deliveryCharge.toLong(),
            nonTaxedTotalPrice = order.payment.nonTaxedTotalPrice.toLong(),
            tax = order.payment.tax.toLong(),
            taxedTotalPrice = order.payment.taxedTotalPrice.toLong(),
            time = order.time,
            blackLevel = order.blackLevel.code,
            createdAt = now,
            updatedAt = now
        )

        ordersMapper.insert(row)
    }

    fun registerOrderItems(orderId: OrderId, orderItems: OrderItems, now: LocalDateTime) {
        val orderItemBaseList = orderItems.value.map {
            OrderItemsBase(
                orderId = orderId.value,
                itemId = it.itemId,
                quantity = it.quantity,
                createdAt = now,
                updatedAt = now
            )
        }

        orderItemsMapper.insertMultiple(orderItemBaseList)
    }

    private fun registerOrderItemAttributes(order: Order, now: LocalDateTime) {
        val orderItemAttributeBases = order.orderItems.value.flatMap { item ->
            val attributes = item.orderItemAttributes
            attributes.value.map { attribute ->
                OrderItemAttributesBase(
                    orderId = order.orderId.value,
                    itemId = item.itemId,
                    attributeId = attribute.attributeId,
                    createdAt = now,
                    updatedAt = now
                )
            }
        }

        orderItemAttributesMapper.insertMultiple(orderItemAttributeBases)
    }
}