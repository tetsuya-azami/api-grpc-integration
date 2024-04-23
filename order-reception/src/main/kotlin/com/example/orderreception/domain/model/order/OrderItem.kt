package com.example.orderreception.domain.model.order

import com.example.orderreception.infrastructure.entity.custom.ItemWithAttributesBase
import com.example.orderreception.presentation.order.OrderItemParam
import java.math.BigDecimal

data class OrderItem private constructor(
    val itemId: Long,
    val name: String,
    val price: BigDecimal,
    val attributes: List<Attribute>,
    val quantity: Int
) {
    companion object {
        fun fromBaseAndParam(
            itemWithAttributesBase: ItemWithAttributesBase,
            orderItemParam: OrderItemParam
        ): OrderItem {
            val attributes = itemWithAttributesBase.attributes.map { attributesBase ->
                Attribute.fromBase(attributesBase = attributesBase)
            }

            return OrderItem(
                itemId = itemWithAttributesBase.itemId!!,
                name = itemWithAttributesBase.name!!,
                price = itemWithAttributesBase.price!!,
                attributes = attributes,
                quantity = orderItemParam.quantity
            )
        }
    }
}