package com.example.orderreception.infrastructure.entity.custom

import com.example.orderreception.infrastructure.entity.generated.AttributesBase

data class ItemWithAttributesBase(
    var itemId: Long? = null,
    var name: String? = null,
    var price: Long? = null,
    var attributes: List<AttributesBase>
)