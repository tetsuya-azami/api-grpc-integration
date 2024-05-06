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
        private const val MY_COMPONENT_NAME = "order-reception"
    }

    override fun <ReqT : Any?, RespT : Any?> interceptCall(
        method: MethodDescriptor<ReqT, RespT>,
        callOptions: CallOptions,
        next: Channel
    ): ClientCall<ReqT, RespT> {
        return object : SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
            override fun start(responseListener: Listener<RespT>, requestHeaders: Metadata) {
                // 送信先のコンポーネントが送信元のコンポーネントを特定するために使用する。
                requestHeaders.put(
                    Metadata.Key.of("REQUEST_COMPONENT_NAME", Metadata.ASCII_STRING_MARSHALLER),
                    MY_COMPONENT_NAME
                )
                logger.info("grpc request headers: {}", requestHeaders)

                val overrideResponseListener = object : SimpleForwardingClientCallListener<RespT>(responseListener) {
                    /**
                     * レスポンス受信時のログ出力
                     */
                    override fun onMessage(message: RespT) {
                        logger.info(
                            "grpc response from method: {}, responseBody: {}",
                            method.fullMethodName,
                            message
                        )
                        super.onMessage(message)
                    }

                    /**
                     * response header受信時のログ出力
                     */
                    override fun onHeaders(responseHeaders: Metadata) {
                        logger.info("grpc response headers: {}", responseHeaders)
                        super.onHeaders(responseHeaders)
                    }

                    /**
                     * 接続close時のログ出力
                     */
                    override fun onClose(status: Status?, trailers: Metadata?) {
                        logger.info("grpc call ended with status: {}, trailers: {}", status, trailers)
                        super.onClose(status, trailers)
                    }
                }

                super.start(overrideResponseListener, requestHeaders)
            }

            /**
             * リクエスト送信時のログ出力
             */
            override fun sendMessage(message: ReqT) {
                logger.info("grpc request method: {}, requestBody: {}", method.fullMethodName, message)
                super.sendMessage(message)
            }
        }
    }
}