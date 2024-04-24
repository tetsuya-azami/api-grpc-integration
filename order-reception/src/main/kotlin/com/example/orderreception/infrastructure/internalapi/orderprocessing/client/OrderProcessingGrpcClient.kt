package com.example.orderreception.infrastructure.internalapi.orderprocessing.client

import com.example.grpcinterface.proto.OrderServiceGrpc.OrderServiceBlockingStub
import com.example.orderreception.domain.model.order.OrderItem
import com.example.orderreception.domain.model.order.User
import com.example.orderreception.infrastructure.internalapi.orderprocessing.request.RegisterOrderRequest
import com.example.orderreception.infrastructure.internalapi.orderprocessing.response.RegisterOrderResponse
import com.example.orderreception.presentation.order.OrderParam
import net.devh.boot.grpc.client.inject.GrpcClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class OrderProcessingGrpcClient(
    @GrpcClient("orderProcessingGrpcClient") private val orderServiceStub: OrderServiceBlockingStub
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun registerOrder(orderParam: OrderParam, items: List<OrderItem>, user: User): RegisterOrderResponse {
        val registerOrderRequest = RegisterOrderRequest.fromModel(orderParam = orderParam, items = items, user = user)

        // TODO: 非同期での連携を検討
        return try {
            val orderCreationResponse = orderServiceStub.createOrder(registerOrderRequest.orderCreationRequest)
            RegisterOrderResponse(orderId = orderCreationResponse.id)
        } catch (e: Exception) {
            logger.warn("OrderProcessingとの通信で予期せぬエラーが発生しました。", e)
            throw e
        }
    }
}