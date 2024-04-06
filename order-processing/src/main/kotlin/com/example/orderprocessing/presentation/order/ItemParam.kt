package com.example.orderprocessing.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass
import java.math.BigDecimal

data class ItemParam private constructor(
    val id: Long,
    val name: String,
    val price: BigDecimal,
    val attributeParams: List<AttributeParam>,
    val quantity: Int
) {
    companion object {
        fun fromProto(itemsProto: List<OrderOuterClass.Item>): List<ItemParam> {
            return itemsProto.map { itemProto ->
                val attributeParams = AttributeParam.fromProto(itemProto.attributesList)
                ItemParam(
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
