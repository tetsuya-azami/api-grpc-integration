package com.example.orderprocessing.domain.model

import com.example.orderprocessing.error.ValidationError
import com.example.orderprocessing.presentation.order.OrderItemParam
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import java.math.BigDecimal


data class OrderItem private constructor(
    val itemId: Long,
    val price: BigDecimal,
    val quantity: Int,
    val orderItemAttributes: OrderItemAttributes
) {
    companion object {
        private const val MINIMUM_QUANTITY = 1
        private const val MAXIMUM_QUANTITY = 100
        fun new(
            itemId: Long,
            price: BigDecimal,
            quantity: Int,
            orderItemAttributes: OrderItemAttributes
        ): Result<OrderItem, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()

            if (quantity !in MINIMUM_QUANTITY..MAXIMUM_QUANTITY) {
                validationErrors.add(
                    ValidationError(
                        fieldName = OrderItemParam::quantity.name,
                        description = "商品の数量は${MINIMUM_QUANTITY}個から${MAXIMUM_QUANTITY}個の間で注文できます。商品ID: $itemId, 数量: $quantity"
                    )
                )
            }

            if (validationErrors.isNotEmpty()) {
                return Err(validationErrors)
            }

            return Ok(
                OrderItem(
                    itemId = itemId,
                    price = price,
                    quantity = quantity,
                    orderItemAttributes = orderItemAttributes
                )
            )
        }
    }

    fun nonTaxedTotalPrice(): BigDecimal {
        return this.price.multiply(this.quantity.toBigDecimal())
    }

    fun attributesSize(): Int {
        return orderItemAttributes.size()
    }
}