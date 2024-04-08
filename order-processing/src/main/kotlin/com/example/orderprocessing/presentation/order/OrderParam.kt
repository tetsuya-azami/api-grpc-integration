package com.example.orderprocessing.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderprocessing.domain.model.BlackLevel
import java.time.LocalDateTime
import java.time.ZoneOffset

data class OrderParam(
    val itemParams: List<OrderItemParam>,
    val chainId: Long,
    val shopId: Long,
    val deliveryParam: DeliveryParam,
    val userParam: UserParam,
    val paymentParam: PaymentParam,
    val blackLevel: BlackLevel,
    val time: LocalDateTime
) {
    companion object {
        fun fromProto(orderProto: OrderOuterClass.Order): OrderParam {
            val orderItemParams = OrderItemParam.fromProto(orderProto.itemsList)
            val deliveryParam = DeliveryParam.fromProto(orderProto.delivery)
            val userParam = UserParam.fromProto(orderProto.user)
            val paymentParam = PaymentParam.fromProto(orderProto.payment)
            val timeProto = orderProto.time

            return OrderParam(
                itemParams = orderItemParams,
                chainId = orderProto.chain.id,
                shopId = orderProto.shop.id,
                deliveryParam = deliveryParam,
                userParam = userParam,
                paymentParam = paymentParam,
                blackLevel = BlackLevel.fromString(orderProto.user.blackLevel.name),
                time = LocalDateTime.ofEpochSecond(timeProto.seconds, timeProto.nanos, ZoneOffset.of("+09:00"))
            )
        }
    }
}
