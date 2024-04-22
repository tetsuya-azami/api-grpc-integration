package com.example.orderreception.domain.model.order

import com.example.orderreception.infrastructure.entity.custom.ItemWithAttributesBase
import com.example.orderreception.presentation.order.ItemParam
import java.math.BigDecimal

data class Item private constructor(
    val itemId: Long,
    val name: String,
    val price: BigDecimal,
    val attributes: List<Attribute>,
    val quantity: Int
) {
    companion object {
        fun fromBaseAndParam(itemWithAttributesBase: ItemWithAttributesBase, itemParam: ItemParam): Item {
            val attributes = itemWithAttributesBase.attributes.map { attributesBase ->
                Attribute.fromBase(attributesBase = attributesBase)
            }

            return Item(
                itemId = itemWithAttributesBase.itemId!!,
                name = itemWithAttributesBase.name!!,
                price = BigDecimal.valueOf(itemWithAttributesBase.price!!),
                attributes = attributes,
                quantity = itemParam.quantity
            )
        }
    }
}