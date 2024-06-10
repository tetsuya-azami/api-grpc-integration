package com.example.orderprocessing.domain.model

import com.example.orderprocessing.error.ValidationError
import com.example.orderprocessing.presentation.order.OrderItemParam
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.getOrElse

data class OrderItemAttributes private constructor(val value: List<OrderItemAttribute>) {
    companion object {
        private const val MINIMUM_ATTRIBUTE_SIZE = 0
        private const val MAXIMUM_ATTRIBUTE_SIZE = 100

        fun fromParam(
            orderItemParam: OrderItemParam
        ): Result<OrderItemAttributes, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()
            val attributeParams = orderItemParam.attributeParams

            if (attributeParams.size !in MINIMUM_ATTRIBUTE_SIZE..MAXIMUM_ATTRIBUTE_SIZE) {
                validationErrors.add(
                    ValidationError(
                        fieldName = OrderItemAttribute::class.simpleName!!,
                        description = "商品属性は${MINIMUM_ATTRIBUTE_SIZE}つから${MAXIMUM_ATTRIBUTE_SIZE}つの間で商品に紐づけることができます。商品ID: ${orderItemParam.id}, 商品属性: ${orderItemParam.attributeParams}"
                    )
                )
            }

            val itemAttributes = attributeParams.mapNotNull {
                OrderItemAttribute.fromParam(it)
                    .getOrElse { errors ->
                        validationErrors.addAll(errors)
                        null
                    }
            }

            return if (validationErrors.isNotEmpty()) Err(validationErrors) else Ok(OrderItemAttributes(value = itemAttributes))
        }
    }

    fun size(): Int {
        return value.size
    }
}