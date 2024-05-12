package com.example.orderreception.infrastructure.api.internal.orderprocessing.client

import io.grpc.Metadata
import io.grpc.ServerCall
import io.grpc.ServerCallHandler
import io.grpc.ServerInterceptor

class TestInterceptor : ServerInterceptor {
    override fun <ReqT : Any?, RespT : Any?> interceptCall(
        serverCall: ServerCall<ReqT, RespT>,
        headers: Metadata,
        handler: ServerCallHandler<ReqT, RespT>
    ): ServerCall.Listener<ReqT> {
        return handler.startCall(serverCall, headers)
    }
}