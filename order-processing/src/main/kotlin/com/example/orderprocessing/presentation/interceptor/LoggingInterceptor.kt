package com.example.orderprocessing.presentation.interceptor

import io.grpc.*
import io.grpc.ForwardingServerCall.SimpleForwardingServerCall
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor
import org.slf4j.LoggerFactory

@GrpcGlobalServerInterceptor
class LoggingInterceptor : ServerInterceptor {
    companion object {
        private val logger = LoggerFactory.getLogger(LoggingInterceptor::class.java)
        private const val MY_COMPONENT_NAME = "order-processing"
    }

    override fun <ReqT : Any?, RespT : Any?> interceptCall(
        serverCall: ServerCall<ReqT, RespT>,
        headers: Metadata,
        serverCallHandler: ServerCallHandler<ReqT, RespT>
    ): ServerCall.Listener<ReqT> {
        val overrideForwardingServerCall = object : SimpleForwardingServerCall<ReqT, RespT>(serverCall) {
            /**
             * response返却時のログ出力
             */
            override fun sendMessage(message: RespT) {
                logger.info(
                    "grpc response to: {}, method: {}, message: {}",
                    headers.get(Metadata.Key.of("REQUEST_COMPONENT_NAME", Metadata.ASCII_STRING_MARSHALLER)),
                    serverCall.methodDescriptor.fullMethodName,
                    message
                )
                super.sendMessage(message)
            }

            /**
             * response header送信時のログ出力
             */
            override fun sendHeaders(headers: Metadata) {
                headers.put(
                    Metadata.Key.of("RESPONSE_COMPONENT_NAME", Metadata.ASCII_STRING_MARSHALLER),
                    MY_COMPONENT_NAME
                )
                logger.info("grpc response headers: {}", headers)
                super.sendHeaders(headers)
            }
        }

        return object : ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(
            serverCallHandler.startCall(overrideForwardingServerCall, headers)
        ) {
            /**
             *  request受信時のログ出力
             */
            override fun onMessage(message: ReqT) {
                logger.info(
                    "grpc request from: {}, method: {}, headers: {}. message: {}",
                    headers.get(Metadata.Key.of("REQUEST_COMPONENT_NAME", Metadata.ASCII_STRING_MARSHALLER)),
                    serverCall.methodDescriptor.fullMethodName,
                    headers,
                    message
                )
                super.onMessage(message)
            }
        }
    }
}