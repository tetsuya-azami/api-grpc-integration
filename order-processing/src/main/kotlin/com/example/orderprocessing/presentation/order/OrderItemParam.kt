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
        fun fromProto(itemsProto: List<OrderOuterClass.Item>): List<OrderItemParam> {
            return itemsProto.map { itemProto ->
                val attributeParams = AttributeParam.fromProto(itemProto.attributesList)
                OrderItemParam(
                    id = itemProto.id,
                    name = itemProto.name,
                    price = BigDecimal.valueOf(itemProto.price.units),
                    attributeParams = attributeParams,
                    quantity = itemProto.quantity
                )
            }
        }
    }
}
