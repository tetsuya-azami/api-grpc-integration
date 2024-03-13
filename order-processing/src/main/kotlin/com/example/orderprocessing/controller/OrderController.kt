package com.example.orderprocessing.controller

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.grpcinterface.proto.OrderServiceGrpcKt
import com.example.orderprocessing.model.order.Order
import com.example.orderprocessing.usecase.command.CreateOrder
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
@Suppress("unused")
class OrderController(private val createOrder: CreateOrder) : OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {
    override suspend fun createOrder(request: OrderOuterClass.OrderCreationRequest): OrderOuterClass.OrderCreationResponse {
        val order = Order.fromOrderCreationRequest(request)
        val orderId = createOrder.execute(order)

        return OrderOuterClass.OrderCreationResponse.newBuilder().setId(orderId.value).build()
    }
}