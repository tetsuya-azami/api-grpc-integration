package com.example.orderprocessing.domain.model

import com.example.orderprocessing.error.ValidationError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import java.math.BigDecimal

data class OrderItems private constructor(val value: List<OrderItem>) {

    companion object {
        private const val MINIMUM_ITEM_SIZE = 1
        private const val MAXIMUM_ITEM_SIZE = 100

        fun new(value: List<OrderItem>): Result<OrderItems, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()

            if (value.size !in MINIMUM_ITEM_SIZE..MAXIMUM_ITEM_SIZE) {
                validationErrors.add(
                    ValidationError(
                        fieldName = "orderItems",
                        description = "商品は${MINIMUM_ITEM_SIZE}個から${MAXIMUM_ITEM_SIZE}個の間で注文できます。"
                    )
                )
            }

            return if (validationErrors.isNotEmpty()) Err(validationErrors) else Ok(OrderItems(value = value))
        }
    }

    fun size(): Int {
        return this.value.size
    }

    fun nonTaxedTotalPrice(): BigDecimal {
        return this.value.sumOf(OrderItem::nonTaxedTotalPrice)
    }
}