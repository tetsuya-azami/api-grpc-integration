package com.example.orderprocessing.controller

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.grpcinterface.proto.OrderServiceGrpcKt
import com.example.orderprocessing.error.ValidationError
import com.example.orderprocessing.model.order.Order
import com.example.orderprocessing.usecase.command.RegisterOrder
import com.github.michaelbull.result.getOrElse
import com.google.rpc.BadRequest
import io.grpc.Metadata
import io.grpc.Status
import io.grpc.StatusException
import io.grpc.protobuf.ProtoUtils
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

        val validationErrors = mutableListOf<ValidationError>()

        val order = Order.fromOrderCreationRequest(request)
            .getOrElse {
                validationErrors.addAll(it)
                null
            }

        if (validationErrors.isNotEmpty() || order == null) {
            val messages = validationErrors.joinToString(separator = "\n") { it.message }

            val fieldViolation = BadRequest.FieldViolation
                .newBuilder()
                .setField("all")
                .setDescription(messages)
                .build()
            val badRequestError = BadRequest.newBuilder().addFieldViolations(fieldViolation).build()

            val errorDetail = Metadata()
            errorDetail.put(ProtoUtils.keyForProto(badRequestError), badRequestError)

            throw StatusException(Status.INVALID_ARGUMENT, errorDetail)
        }

        val orderId = registerOrder.execute(order)

        val response =
            OrderOuterClass.OrderCreationResponse
                .newBuilder()
                .setId(orderId.value)
                .build()

        logger.info("send response: $response")

        return response
    }
}