package com.example.orderprocessing.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderprocessing.domain.model.BlackLevel

data class UserParam(
    val id: Long,
    val blackLevel: BlackLevel
) {
    companion object {
        fun fromProto(userProto: OrderOuterClass.User): UserParam {
            return UserParam(
                id = userProto.id,
                blackLevel = BlackLevel.fromString(userProto.blackLevel.name)
            )
        }
    }
}