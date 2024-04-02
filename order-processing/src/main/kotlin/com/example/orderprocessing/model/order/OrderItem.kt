package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderprocessing.error.ValidationError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.getOrElse


class OrderItem private constructor(
    val itemId: Long,
    val price: Long,
    val quantity: Int,
    val attributes: OrderItemAttributes
) {
    companion object {
        const val MINIMUM_QUANTITY = 1
        const val MAXIMUM_QUANTITY = 100
        fun fromOrderCreationRequest(orderItemProto: OrderOuterClass.Item): Result<OrderItem, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()

            if (orderItemProto.quantity !in MINIMUM_QUANTITY..MAXIMUM_QUANTITY) {
                validationErrors.add(OrderItemValidationErrors.IllegalItemQuantity(orderItemProto))
            }

            val orderItemAttributes = OrderItemAttributes
                .fromOrderCreationRequest(orderItemProto)
                .getOrElse {
                    validationErrors.addAll(it)
                    null
                }

            if (validationErrors.isNotEmpty() || orderItemAttributes == null) {
                return Err(validationErrors)
            }

            return Ok(
                OrderItem(
                    itemId = orderItemProto.id,
                    price = orderItemProto.price.units,
                    orderItemProto.quantity,
                    attributes = orderItemAttributes
                )
            )
        }
    }

    fun nonTaxedTotalPrice(): Long {
        return this.price * this.quantity
    }

    sealed interface OrderItemValidationErrors : ValidationError {
        data class IllegalItemQuantity(val orderItem: OrderOuterClass.Item) : OrderItemValidationErrors {
            override val message: String
                get() = "商品の数量は${MINIMUM_QUANTITY}個から${MAXIMUM_QUANTITY}個の間で注文できます。商品ID: ${orderItem.id}, 数量: ${orderItem.quantity}"
        }
    }

    fun attributesSize(): Int {
        return attributes.size()
    }
}