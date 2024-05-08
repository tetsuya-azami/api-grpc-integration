package com.example.orderreception.infrastructure.api.internal.orderprocessing.client

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.grpcinterface.proto.OrderOuterClass.OrderCreationResponse
import com.example.grpcinterface.proto.OrderServiceGrpcKt
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class OrderProcessingServerImplForIntegrationTest : OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {

    override suspend fun createOrder(request: OrderOuterClass.OrderCreationRequest): OrderCreationResponse {
        return OrderCreationResponse.newBuilder()
            .setId("testId")
            .build()
    }
}