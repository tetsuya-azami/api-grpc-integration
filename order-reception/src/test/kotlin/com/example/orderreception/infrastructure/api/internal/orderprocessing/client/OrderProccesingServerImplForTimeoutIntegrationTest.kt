package com.example.orderreception.infrastructure.api.internal.orderprocessing.client

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.grpcinterface.proto.OrderServiceGrpcKt

class OrderProccesingServerImplForTimeoutIntegrationTest : OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {

    override suspend fun createOrder(request: OrderOuterClass.OrderCreationRequest): OrderOuterClass.OrderCreationResponse {
        // Client側で設定されたタイムアウトより長い時間sleepさせる。
        Thread.sleep(15000)

        throw RuntimeException("Timeoutの設定がうまく効いていません。")
    }
}