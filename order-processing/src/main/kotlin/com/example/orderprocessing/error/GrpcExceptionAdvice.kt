package com.example.orderprocessing.error

import com.example.orderprocessing.error.exception.OrderProcessingIllegalArgumentException
import com.google.rpc.BadRequest
import io.grpc.Metadata
import io.grpc.Status
import io.grpc.StatusException
import io.grpc.protobuf.ProtoUtils
import net.devh.boot.grpc.server.advice.GrpcAdvice
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler

@GrpcAdvice
class GrpcExceptionAdvice {

    @GrpcExceptionHandler
    fun handleInvalidArgument(illegalArgumentException: OrderProcessingIllegalArgumentException): StatusException {
        val validationErrors = illegalArgumentException.validationErrors
        val messages = validationErrors.joinToString(separator = "\n") { it.message }

        // TODO: Filedの見直し
        val fieldViolation = BadRequest.FieldViolation
            .newBuilder()
            .setField("all")
            .setDescription(messages)
            .build()
        val badRequestError = BadRequest
            .newBuilder()
            .addFieldViolations(fieldViolation)
            .build()

        val errorDetail = Metadata()
        errorDetail.put(ProtoUtils.keyForProto(badRequestError), badRequestError)

        val status = Status.INVALID_ARGUMENT
            .withDescription("リクエストが不正です。")
            .withCause(illegalArgumentException)

        return status.asException(errorDetail)
    }
}