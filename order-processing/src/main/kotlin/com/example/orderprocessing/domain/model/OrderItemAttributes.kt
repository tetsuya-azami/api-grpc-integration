package com.example.orderprocessing.domain.model

import com.example.orderprocessing.error.ValidationError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

data class OrderItemAttributes private constructor(
    val itemId: Long,
    val value: List<OrderItemAttribute>
) {
    companion object {
        private const val MINIMUM_ATTRIBUTE_SIZE = 0
        private const val MAXIMUM_ATTRIBUTE_SIZE = 100

        fun new(
            itemId: Long,
            value: List<OrderItemAttribute>
        ): Result<OrderItemAttributes, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()

            if (value.size !in MINIMUM_ATTRIBUTE_SIZE..MAXIMUM_ATTRIBUTE_SIZE) {
                validationErrors.add(
                    ValidationError(
                        fieldName = OrderItemAttribute::class.simpleName!!,
                        description = "商品属性は${MINIMUM_ATTRIBUTE_SIZE}つから${MAXIMUM_ATTRIBUTE_SIZE}つの間で商品に紐づけることができます。商品ID: $itemId, 商品属性: $value"
                    )
                )
            }

            return if (validationErrors.isNotEmpty()) Err(validationErrors) else Ok(
                OrderItemAttributes(
                    itemId = itemId,
                    value = value
                )
            )
        }
    }

    fun size(): Int {
        return value.size
    }
}