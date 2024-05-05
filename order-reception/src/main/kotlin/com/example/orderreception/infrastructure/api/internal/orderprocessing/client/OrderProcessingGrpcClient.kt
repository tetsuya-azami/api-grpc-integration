package com.example.orderreception.infrastructure.api.internal.orderprocessing.client

import com.example.grpcinterface.proto.OrderServiceGrpc.OrderServiceBlockingStub
import com.example.orderreception.domain.model.order.OrderItem
import com.example.orderreception.domain.model.order.User
import com.example.orderreception.error.ValidationError
import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException
import com.example.orderreception.infrastructure.api.internal.orderprocessing.request.RegisterOrderRequest
import com.example.orderreception.infrastructure.api.internal.orderprocessing.response.RegisterOrderResponse
import com.example.orderreception.presentation.order.OrderParam
import com.google.rpc.BadRequest
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.protobuf.ProtoUtils
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
            return RegisterOrderResponse(orderId = orderCreationResponse.id)
        } catch (e: StatusRuntimeException) {
            logger.warn("OrderProcessingとの通信で予期せぬエラーが発生しました。", e)
            if (e.status.code == Status.INVALID_ARGUMENT.code) {
                val errorDetails = e.trailers?.get(ProtoUtils.keyForProto(BadRequest.getDefaultInstance()))
                val validationErrors = errorDetails?.fieldViolationsList?.map {
                    ValidationError(field = it.field, message = it.description)
                }
                logger.warn("バリデーションエラーリスト: $validationErrors")
                throw OrderReceptionIllegalArgumentException(validationErrors = validationErrors!!)
            }
            throw e
        } catch (e: Exception) {
            logger.warn("OrderProcessingとの通信で予期せぬエラーが発生しました。", e)
            throw e
        }
    }
}