package com.example.orderreception.infrastructure.entity.custom

import com.example.orderreception.infrastructure.entity.generated.AttributesBase
import java.math.BigDecimal

data class ItemWithAttributesBase(
    var itemId: Long? = null,
    var name: String? = null,
    var price: BigDecimal? = null,
    var attributes: List<AttributesBase>
)