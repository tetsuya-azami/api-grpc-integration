package com.example.orderprocessing.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass

data class UserParam(
    val id: Long,
) {
    companion object {
        fun fromProto(userProto: OrderOuterClass.User): UserParam {
            return UserParam(id = userProto.id)
        }
    }
}