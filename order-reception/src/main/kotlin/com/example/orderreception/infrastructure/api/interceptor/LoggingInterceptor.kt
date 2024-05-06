package com.example.orderreception.infrastructure.api.interceptor

import io.grpc.*
import io.grpc.ForwardingClientCall.SimpleForwardingClientCall
import io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor
import org.slf4j.LoggerFactory

@GrpcGlobalClientInterceptor
class LoggingInterceptor : ClientInterceptor {
    companion object {
        private val logger = LoggerFactory.getLogger(LoggingInterceptor::class.java)
    }

    override fun <ReqT : Any?, RespT : Any?> interceptCall(
        method: MethodDescriptor<ReqT, RespT>,
        callOptions: CallOptions,
        next: Channel
    ): ClientCall<ReqT, RespT> {
        return object : SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
            override fun start(responseListener: Listener<RespT>, headers: Metadata) {
                logger.info("grpc request header: {}", headers)

                val overrideResponseListener = object : SimpleForwardingClientCallListener<RespT>(responseListener) {
                    /**
                     * レスポンス受信時のログ出力
                     */
                    override fun onMessage(message: RespT) {
                        logger.info("grpc response from: {}, responseBody: {}", method.fullMethodName, message)
                        super.onMessage(message)
                    }

                    /**
                     * 接続close時のログ出力
                     */
                    override fun onClose(status: Status?, trailers: Metadata?) {
                        logger.info("grpc call ended with status: {}, trailers: {}", status, trailers)
                        super.onClose(status, trailers)
                    }
                }

                super.start(overrideResponseListener, headers)
            }

            override fun sendMessage(message: ReqT) {
                logger.info("grpc request to: {}, requestBody: {}", method.fullMethodName, message)
                super.sendMessage(message)
            }
        }
    }
}