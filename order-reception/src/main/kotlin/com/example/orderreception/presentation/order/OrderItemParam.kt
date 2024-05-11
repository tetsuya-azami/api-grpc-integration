package com.example.orderreception.presentation.order

import com.example.orderreception.error.ValidationError
import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException
import java.math.BigDecimal

data class OrderItemParam private constructor(
    val itemId: Long,
    val price: BigDecimal,
    val attributes: List<AttributeParam>,
    val quantity: Int
) {
    companion object {
        fun new(
            itemId: Long?,
            price: BigDecimal?,
            attributes: List<AttributeParam>?,
            quantity: Int?
        ): OrderItemParam {
            val validationErrors = mutableListOf<ValidationError>()

            if (itemId == null) {
                validationErrors.add(
                    ValidationError(
                        field = "item",
                        message = "商品IDがありません。"
                    )
                )
            }
            if (price == null) {
                validationErrors.add(
                    ValidationError(
                        field = "item",
                        message = "商品価格がありません。"
                    )
                )
            }
            if (attributes.isNullOrEmpty()) {
                validationErrors.add(
                    ValidationError(
                        field = "item",
                        message = "商品属性情報がありません。"
                    )
                )
            }
            if (quantity == null) {
                validationErrors.add(
                    ValidationError(
                        field = "item",
                        message = "購入数量がありません。"
                    )
                )
            }

            if (validationErrors.isNotEmpty()) {
                throw OrderReceptionIllegalArgumentException(validationErrors = validationErrors)
            }

            return OrderItemParam(
                itemId = itemId!!,
                price = price!!,
                attributes = attributes!!,
                quantity = quantity!!,
            )
        }
    }
}