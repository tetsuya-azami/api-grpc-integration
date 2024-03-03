package com.example.orderprocessing.controller

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.grpcinterface.proto.OrderServiceGrpcKt
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class OrderController : OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {
    override suspend fun createOrder(request: OrderOuterClass.OrderCreationRequest): OrderOuterClass.OrderCreationResponse {
        //　TODO: order作成処理(DB登録等)
        println(request)
        // TODO: 作成したorderのIDを返す
        return OrderOuterClass.OrderCreationResponse.newBuilder().setId(1).build()
    }
}