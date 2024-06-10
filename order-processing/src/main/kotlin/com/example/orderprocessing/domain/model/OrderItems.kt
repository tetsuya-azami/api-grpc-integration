package com.example.orderprocessing.domain.model

import com.example.orderprocessing.error.ValidationError
import com.example.orderprocessing.presentation.order.OrderItemParam
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.getOrElse
import java.math.BigDecimal

data class OrderItems private constructor(val value: List<OrderItem>) {

    companion object {
        private const val MINIMUM_ITEM_SIZE = 1
        private const val MAXIMUM_ITEM_SIZE = 100

        fun fromParam(itemParams: List<OrderItemParam>): Result<OrderItems, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()

            if (itemParams.size !in MINIMUM_ITEM_SIZE..MAXIMUM_ITEM_SIZE) {
                validationErrors.add(
                    ValidationError(
                        fieldName = "orderItems",
                        description = "商品は${MINIMUM_ITEM_SIZE}個から${MAXIMUM_ITEM_SIZE}個の間で注文できます。"
                    )
                )
            }

            val orderItems = itemParams.mapNotNull {
                OrderItem.fromParam(it)
                    .getOrElse { errors ->
                        validationErrors.addAll(errors)
                        null
                    }
            }

            return if (validationErrors.isNotEmpty()) Err(validationErrors) else Ok(OrderItems(value = orderItems))
        }
    }

    fun nonTaxedTotalPrice(): BigDecimal {
        return this.value.sumOf(OrderItem::nonTaxedTotalPrice)
    }
}