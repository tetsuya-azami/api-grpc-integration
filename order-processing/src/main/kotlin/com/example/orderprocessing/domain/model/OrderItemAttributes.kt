package com.example.orderprocessing.domain.model

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderprocessing.domain.model.OrderItemAttributes.OrderItemAttributesValidationError.IllegalOrderItemAttributeSize
import com.example.orderprocessing.error.ValidationError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.getOrElse

class OrderItemAttributes private constructor(val value: List<OrderItemAttribute>) {
    companion object {
        const val MINIMUM_ATTRIBUTE_SIZE = 0
        const val MAXIMUM_ATTRIBUTE_SIZE = 100

        fun fromOrderCreationRequest(
            orderItemProto: OrderOuterClass.Item
        ): Result<OrderItemAttributes, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()
            val itemAttributesProto = orderItemProto.attributesList

            if (itemAttributesProto.size !in MINIMUM_ATTRIBUTE_SIZE..MAXIMUM_ATTRIBUTE_SIZE) {
                validationErrors.add(IllegalOrderItemAttributeSize(orderItemProto))
            }

            val itemAttributes = itemAttributesProto.mapNotNull {
                OrderItemAttribute.fromOrderCreationRequest(it)
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

    sealed interface OrderItemAttributesValidationError : ValidationError {
        data class IllegalOrderItemAttributeSize(val orderItem: OrderOuterClass.Item) :
            OrderItemAttributesValidationError {
            override val message: String
                get() = "商品属性は${MINIMUM_ATTRIBUTE_SIZE}文字から${MAXIMUM_ATTRIBUTE_SIZE}文字の間で商品に紐づけることができます。商品ID: ${orderItem.id}, 商品属性: ${orderItem.attributesList}"
        }
    }
}