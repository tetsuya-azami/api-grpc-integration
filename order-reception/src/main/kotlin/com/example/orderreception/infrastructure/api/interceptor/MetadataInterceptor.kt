package com.example.orderreception.infrastructure.api.interceptor

import io.grpc.*
import io.grpc.ForwardingClientCall.SimpleForwardingClientCall
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor

@GrpcGlobalClientInterceptor
class MetadataInterceptor : ClientInterceptor {
    override fun <ReqT : Any?, RespT : Any?> interceptCall(
        method: MethodDescriptor<ReqT, RespT>,
        callOptions: CallOptions,
        channel: Channel
    ): ClientCall<ReqT, RespT> {
        return object : SimpleForwardingClientCall<ReqT, RespT>(channel.newCall(method, callOptions)) {
            override fun start(responseListener: Listener<RespT>?, headers: Metadata) {
                headers.put(
                    Metadata.Key.of("REQUEST_COMPONENT_NAME", Metadata.ASCII_STRING_MARSHALLER),
                    "order-reception"
                )
                super.start(responseListener, headers)
            }
        }
    }
}