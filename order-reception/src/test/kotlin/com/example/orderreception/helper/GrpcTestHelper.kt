package com.example.orderreception.helper

import com.google.rpc.BadRequest
import io.grpc.Metadata
import io.grpc.protobuf.ProtoUtils

class GrpcTestHelper {
    companion object {
        fun getErrorTrailers(): Metadata {
            val trailers = Metadata()

            BadRequest.newBuilder()
                .addFieldViolations(
                    BadRequest.FieldViolation.newBuilder()
                        .setField("フィールド1")
                        .setDescription("エラー1")
                        .build()
                )
                .addFieldViolations(
                    BadRequest.FieldViolation.newBuilder()
                        .setField("フィールド2")
                        .setDescription("エラー2")
                        .build()
                )
                .build()
                .let {
                    trailers.put(ProtoUtils.keyForProto(it), it)
                }

            return trailers
        }
    }
}