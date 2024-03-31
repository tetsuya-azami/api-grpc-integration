package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderprocessing.error.ValidationError


class OrderItem private constructor(
    val itemId: Long,
    val price: Long,
    val quantity: Int,
    val attributes: OrderItemAttributes
) {
    companion object {
        const val MINIMUM_QUANTITY = 1
        const val MAXIMUM_QUANTITY = 100
        fun fromOrderCreationRequest(orderItem: OrderOuterClass.Item): OrderItemValidationResult {
            val validationErrors = mutableListOf<ValidationError>()

            if (orderItem.quantity < MINIMUM_QUANTITY || MAXIMUM_QUANTITY < orderItem.quantity) {
                validationErrors.add(OrderItemValidationErrors.IllegalItemQuantity(orderItem))
            }

            when (val orderItemAttributesValidationResult = OrderItemAttributes.fromOrderCreationRequest(orderItem)) {
                is OrderItemAttributes.OrderItemAttributesValidationResult.Success -> {
                    if (validationErrors.isNotEmpty()) return OrderItemValidationResult.Failure(validationErrors)
                    return OrderItemValidationResult.Success(
                        OrderItem(
                            itemId = orderItem.id,
                            price = orderItem.price.units,
                            quantity = orderItem.quantity,
                            attributes = orderItemAttributesValidationResult.orderItemAttributes
                        )
                    )
                }

                is OrderItemAttributes.OrderItemAttributesValidationResult.Failure -> {
                    validationErrors.addAll(orderItemAttributesValidationResult.validationErrors)
                    return OrderItemValidationResult.Failure(validationErrors)
                }
            }
        }
    }

    sealed interface OrderItemValidationResult {
        data class Success(val orderItem: OrderItem) : OrderItemValidationResult
        data class Failure(val validationErrors: List<ValidationError>) : OrderItemValidationResult
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