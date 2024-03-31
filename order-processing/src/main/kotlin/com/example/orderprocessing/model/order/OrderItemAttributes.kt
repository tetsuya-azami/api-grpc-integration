package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderprocessing.error.ValidationError
import com.example.orderprocessing.model.order.OrderItemAttributes.OrderItemAttributesValidationError.IllegalOrderItemAttributeSize
import com.example.orderprocessing.model.order.OrderItemAttributes.OrderItemAttributesValidationResult.Failure
import com.example.orderprocessing.model.order.OrderItemAttributes.OrderItemAttributesValidationResult.Success

class OrderItemAttributes(val value: List<OrderItemAttribute>) {
    companion object {
        const val MINIMUM_ATTRIBUTE_SIZE = 0
        const val MAXIMUM_ATTRIBUTE_SIZE = 100

        fun fromOrderCreationRequest(
            orderItem: OrderOuterClass.Item
        ): OrderItemAttributesValidationResult {
            val validationErrors = mutableListOf<ValidationError>()
            val itemAttributes = orderItem.attributesList

            if (MAXIMUM_ATTRIBUTE_SIZE < itemAttributes.size) {
                validationErrors.add(IllegalOrderItemAttributeSize(orderItem))
                return Failure(validationErrors)
            }

            val attributes = itemAttributes.map { OrderItemAttribute.fromOrderCreationRequest(it) }

            return Success(OrderItemAttributes(value = attributes))
        }
    }

    fun size(): Int {
        return value.size
    }

    sealed interface OrderItemAttributesValidationResult {
        data class Success(val orderItemAttributes: OrderItemAttributes) : OrderItemAttributesValidationResult
        data class Failure(val validationErrors: List<ValidationError>) : OrderItemAttributesValidationResult
    }

    sealed interface OrderItemAttributesValidationError : ValidationError {
        data class IllegalOrderItemAttributeSize(val orderItem: OrderOuterClass.Item) :
            OrderItemAttributesValidationError {
            override val message: String
                get() = "商品属性は${MINIMUM_ATTRIBUTE_SIZE}文字から${MAXIMUM_ATTRIBUTE_SIZE}文字の間で商品に紐づけることができます。商品ID: ${orderItem.id}, 商品属性: ${orderItem.attributesList}"
        }
    }
}