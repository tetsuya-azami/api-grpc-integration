package com.example.orderreception.presentation.order

import com.example.orderreception.error.ValidationError
import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException

data class AttributeParam(val attributeId: Long) {
    companion object {
        fun new(attributeId: Long?): AttributeParam {
            if (attributeId == null) {
                throw OrderReceptionIllegalArgumentException(
                    listOf(ValidationError("attribute", "属性情報がありません。"))
                )
            }
            
            return AttributeParam(
                attributeId = attributeId
            )
        }
    }
}