package com.example.orderprocessing.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass
import java.time.LocalDateTime
import java.time.ZoneOffset

data class OrderParam private constructor(
    val items: List<ItemParam>,
    val chainId: Long,
    val shopId: Long,
    val delivery: DeliveryParam,
    val user: UserParam,
    val payment: PaymentParam,
    val time: LocalDateTime
) {
    companion object {
        fun fromProto(orderProto: OrderOuterClass.Order): OrderParam {
            val itemParams = ItemParam.fromProto(orderProto.itemsList)
            val deliveryParam = DeliveryParam.fromProto(orderProto.delivery)
            val userParam = UserParam.fromProto(orderProto.user)
            val paymentParam = PaymentParam.fromProto(orderProto.payment)
            val timeProto = orderProto.time
            
            return OrderParam(
                items = itemParams,
                chainId = orderProto.chain.id,
                shopId = orderProto.shop.id,
                delivery = deliveryParam,
                user = userParam,
                payment = paymentParam,
                time = LocalDateTime.ofEpochSecond(timeProto.seconds, timeProto.nanos, ZoneOffset.of("+09:00"))
            )
        }
    }
}
