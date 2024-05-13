package com.example.orderreception.infrastructure.api.internal.orderprocessing.client

import com.example.grpcinterface.proto.OrderServiceGrpcKt
import com.example.orderreception.helper.GrpcTestHelper
import io.grpc.Status

class OrderProcessingServerImplForInvalidArgumentIntegrationTest : OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {
    override suspend fun createOrder(request: com.example.grpcinterface.proto.OrderOuterClass.OrderCreationRequest): com.example.grpcinterface.proto.OrderOuterClass.OrderCreationResponse {
        throw Status.INVALID_ARGUMENT
            .withDescription("リクエストが不正です。")
            .withCause(IllegalArgumentException("元例外エラーメッセージ"))
            .asRuntimeException(GrpcTestHelper.getErrorTrailers())
    }
}