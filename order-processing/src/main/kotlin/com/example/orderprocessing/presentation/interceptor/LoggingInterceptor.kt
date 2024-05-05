package com.example.orderprocessing.presentation.interceptor

import io.grpc.*
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor
import org.slf4j.LoggerFactory

@GrpcGlobalServerInterceptor
class LoggingInterceptor : ServerInterceptor {
    companion object {
        private val logger = LoggerFactory.getLogger(LoggingInterceptor::class.java)
    }

    override fun <ReqT : Any?, RespT : Any?> interceptCall(
        serverCall: ServerCall<ReqT, RespT>,
        headers: Metadata,
        serverCallHandler: ServerCallHandler<ReqT, RespT>
    ): ServerCall.Listener<ReqT> {
        logger.info(
            "method: {}, metaData: {}",
            serverCall.methodDescriptor.fullMethodName,
            headers
        )

        return object : ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(
            serverCallHandler.startCall(serverCall, headers)
        ) {
            override fun onMessage(message: ReqT) {
                logger.info("Received message: $message")
                super.onMessage(message)
            }
        }
    }
}