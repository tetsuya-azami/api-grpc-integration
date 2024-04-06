package com.example.orderprocessing.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass

data class AttributeParam private constructor(val id: Long, val name: String, val value: String) {
    companion object {
        fun fromProto(attributesProto: List<OrderOuterClass.Item.Attribute>): List<AttributeParam> {
            return attributesProto.map {
                AttributeParam(
                    id = it.id,
                    name = it.name,
                    value = it.value
                )
            }
        }
    }
}
