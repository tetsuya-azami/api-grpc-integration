package com.example.orderprocessing.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.grpcinterface.proto.OrderServiceGrpcKt
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
        val orderParam = OrderParam.fromProto(request.order)

        val orderId = registerOrder.execute(orderParam)

        return OrderOuterClass.OrderCreationResponse
            .newBuilder()
            .setId(orderId.value)
            .build()
            .let {
                logger.info("send response: $it")
                it
            }
    }
}