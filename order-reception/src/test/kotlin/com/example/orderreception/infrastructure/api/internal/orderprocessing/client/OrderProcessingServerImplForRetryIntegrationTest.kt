package com.example.orderreception.infrastructure.api.internal.orderprocessing.client

import com.example.grpcinterface.proto.OrderOuterClass.OrderCreationResponse
import com.example.grpcinterface.proto.OrderServiceGrpcKt
import io.grpc.Status
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class OrderProcessingServerImplForRetryIntegrationTest : OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {
    var count = 0
    override suspend fun createOrder(request: com.example.grpcinterface.proto.OrderOuterClass.OrderCreationRequest): OrderCreationResponse {
        this.count++
        throw Status.UNAVAILABLE
            .withDescription("一時的にサーバーが利用できません。")
            .withCause(RuntimeException("元例外エラーメッセージ"))
            .asRuntimeException()
    }
}