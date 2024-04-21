package com.example.orderreception.domain.model.order

import com.example.orderreception.infrastructure.entity.generated.AttributesBase
import com.example.orderreception.infrastructure.entity.generated.ItemsBase
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
        fun fromBaseAndParam(itemsBase: ItemsBase, attributesBases: List<AttributesBase>, itemParam: ItemParam): Item {
            val attributes = attributesBases.map { attributesBase ->
                Attribute.fromBase(attributesBase = attributesBase)
            }

            return Item(
                itemId = itemsBase.itemId!!,
                name = itemsBase.name!!,
                price = BigDecimal.valueOf(itemsBase.price!!),
                attributes = attributes,
                quantity = itemParam.quantity
            )
        }
    }
}