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

        // TODO: Filedの見直し
        val fieldViolations = validationErrors.map { error ->
            BadRequest.FieldViolation.newBuilder()
                .setField(error.fieldName)
                .setDescription(error.description)
                .build()
        }
        val badRequestError = BadRequest.newBuilder().addAllFieldViolations(fieldViolations).build()

        val errorDetail = Metadata()
        errorDetail.put(ProtoUtils.keyForProto(badRequestError), badRequestError)

        val status = Status.INVALID_ARGUMENT
            .withDescription("リクエストが不正です。")
            .withCause(illegalArgumentException)

        return status.asException(errorDetail)
    }
}