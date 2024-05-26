package com.example.orderprocessing.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass
import java.math.BigDecimal

data class OrderItemParam(
    val id: Long,
    val name: String,
    val price: BigDecimal,
    val attributeParams: List<AttributeParam>,
    val quantity: Int
) {
    companion object {
        fun fromProto(itemsProto: List<OrderOuterClass.OrderItem>): List<OrderItemParam> {
            return itemsProto.map { orderItemProto ->
                val attributeParams = AttributeParam.fromProto(orderItemProto.attributesList)
                OrderItemParam(
                    id = orderItemProto.id,
                    name = orderItemProto.name,
                    price = BigDecimal.valueOf(orderItemProto.price.units),
                    attributeParams = attributeParams,
                    quantity = orderItemProto.quantity
                )
            }
        }
    }
}
