package com.example.orderprocessing.repository

import com.example.orderprocessing.repository.mapper.generated.OrdersMapper
import com.example.orderprocessing.repository.mapper.generated.insert
import org.springframework.stereotype.Repository

@Repository
class OrderRepository(private val ordersMapper: OrdersMapper) {
    fun createOrder() {
        
    }
}