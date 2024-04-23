package com.example.orderreception.presentation.order

import com.example.orderreception.error.ValidationError
import com.example.orderreception.openapi.model.Item
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.getOrElse
import java.math.BigDecimal

data class OrderItemParam(
    val itemId: Long,
    val price: BigDecimal,
    val attributes: List<AttributeParam>,
    val quantity: Int
) {
    companion object {
        fun fromOpenApi(item: Item): Result<OrderItemParam, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()
            if (item.id == null) validationErrors.add(ValidationError(message = "商品IDがありません。"))
            if (item.price == null) validationErrors.add(ValidationError(message = "商品価格がありません。"))
            if (item.attributes == null) validationErrors.add(ValidationError(message = "商品属性情報がありません。"))
            if (item.quantity == null) validationErrors.add(ValidationError(message = "購入数量がありません。"))

            val attributeParams = item.attributes!!.map { AttributeParam.fromOpenApi(it) }.mapNotNull { result ->
                result.getOrElse { errors ->
                    validationErrors.addAll(errors)
                    null
                }
            }

            return if (validationErrors.isNotEmpty()) Err(validationErrors)
            else Ok(
                OrderItemParam(
                    itemId = item.id!!,
                    price = BigDecimal.valueOf(item.price!!),
                    attributes = attributeParams,
                    quantity = item.quantity!!
                )
            )
        }
    }
}