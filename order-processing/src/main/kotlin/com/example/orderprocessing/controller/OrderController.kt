package com.example.orderprocessing.controller

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.grpcinterface.proto.OrderServiceGrpcKt
import com.example.orderprocessing.service.OrderService
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
@Suppress("unused")
class OrderController(private val orderService: OrderService) : OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {
    override suspend fun createOrder(request: OrderOuterClass.OrderCreationRequest): OrderOuterClass.OrderCreationResponse {
        println(request)
        orderService.createOrder(request)

        // TODO: 作成したorderのIDを返す
        return OrderOuterClass.OrderCreationResponse.newBuilder().setId(1).build()
    }
}