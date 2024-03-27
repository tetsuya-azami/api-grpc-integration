package com.example.orderprocessing.controller

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.grpcinterface.proto.OrderServiceGrpcKt
import com.example.orderprocessing.model.order.Order
import com.example.orderprocessing.usecase.command.RegisterOrder
import net.devh.boot.grpc.server.service.GrpcService
import org.slf4j.LoggerFactory

@GrpcService
@Suppress("unused")
class OrderController(
    private val registerOrder: RegisterOrder
) : OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun createOrder(request: OrderOuterClass.OrderCreationRequest): OrderOuterClass.OrderCreationResponse {
        logger.info("receive request: $request")

        val order = Order.fromOrderCreationRequest(request)
        val orderId = registerOrder.execute(order)

        val response = OrderOuterClass.OrderCreationResponse.newBuilder().setId(orderId.value).build()

        logger.info("send response: $response")
        
        return response
    }
}