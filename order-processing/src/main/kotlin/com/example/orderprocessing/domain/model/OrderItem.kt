package com.example.orderprocessing.domain.model

import com.example.orderprocessing.error.ValidationError
import com.example.orderprocessing.presentation.order.OrderItemParam
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.getOrElse
import java.math.BigDecimal


data class OrderItem private constructor(
    val itemId: Long,
    val price: BigDecimal,
    val quantity: Int,
    val attributes: OrderItemAttributes
) {
    companion object {
        const val MINIMUM_QUANTITY = 1
        const val MAXIMUM_QUANTITY = 100
        fun fromParam(orderItemParam: OrderItemParam): Result<OrderItem, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()

            if (orderItemParam.quantity !in MINIMUM_QUANTITY..MAXIMUM_QUANTITY) {
                validationErrors.add(OrderItemValidationErrors.IllegalItemQuantity(orderItemParam))
            }

            val orderItemAttributes = OrderItemAttributes.fromParam(orderItemParam)
                .getOrElse {
                    validationErrors.addAll(it)
                    null
                }

            if (validationErrors.isNotEmpty() || orderItemAttributes == null) {
                return Err(validationErrors)
            }

            return Ok(
                OrderItem(
                    itemId = orderItemParam.id,
                    price = orderItemParam.price,
                    quantity = orderItemParam.quantity,
                    attributes = orderItemAttributes
                )
            )
        }
    }

    fun nonTaxedTotalPrice(): BigDecimal {
        return this.price.multiply(this.quantity.toBigDecimal())
    }

    sealed interface OrderItemValidationErrors : ValidationError {
        data class IllegalItemQuantity(val orderItemParam: OrderItemParam) : OrderItemValidationErrors {
            override val fieldName: String
                get() = OrderItemParam::quantity.name
            override val description: String
                get() = "商品の数量は${MINIMUM_QUANTITY}個から${MAXIMUM_QUANTITY}個の間で注文できます。商品ID: ${orderItemParam.id}, 数量: ${orderItemParam.quantity}"
        }
    }

    fun attributesSize(): Int {
        return attributes.size()
    }
}