package com.example.orderprocessing.domain.model

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderprocessing.error.ValidationError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.getOrElse

data class OrderItems private constructor(val value: List<OrderItem>) {

    companion object {
        private const val MINIMUM_ITEM_SIZE = 1
        private const val MAXIMUM_ITEM_SIZE = 100

        fun fromOrderCreationRequest(itemListProto: List<OrderOuterClass.Item>): Result<OrderItems, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()

            if (itemListProto.size !in MINIMUM_ITEM_SIZE..MAXIMUM_ITEM_SIZE) {
                validationErrors.add(OrderItemsValidationError.IllegalItemSize)
            }

            val orderItems = itemListProto.mapNotNull {
                OrderItem.fromOrderCreationRequest(it)
                    .getOrElse { errors ->
                        validationErrors.addAll(errors)
                        null
                    }
            }

            return if (validationErrors.isNotEmpty()) Err(validationErrors) else Ok(OrderItems(value = orderItems))
        }
    }

    fun size(): Int {
        return value.size
    }

    fun nonTaxedTotalPrice(): Long {
        return this.value.sumOf(OrderItem::nonTaxedTotalPrice)
    }

    sealed interface OrderItemsValidationError {
        data object IllegalItemSize : ValidationError {
            override val message: String
                get() = "商品は${MINIMUM_ITEM_SIZE}個から${MAXIMUM_ITEM_SIZE}個の間で注文できます。"
        }
    }
}