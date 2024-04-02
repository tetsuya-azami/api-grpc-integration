package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderprocessing.error.ValidationError
import com.example.orderprocessing.model.order.OrderItemAttributes.OrderItemAttributesValidationError.IllegalOrderItemAttributeSize
import com.github.michaelbull.result.*
import kotlin.collections.fold

class OrderItemAttributes private constructor(val value: List<OrderItemAttribute>) {
    companion object {
        const val MINIMUM_ATTRIBUTE_SIZE = 0
        const val MAXIMUM_ATTRIBUTE_SIZE = 100

        fun fromOrderCreationRequest(
            orderItem: OrderOuterClass.Item
        ): Result<OrderItemAttributes, List<ValidationError>> {
            val validationErrors = mutableListOf<ValidationError>()
            val itemAttributesProto = orderItem.attributesList

            if (MAXIMUM_ATTRIBUTE_SIZE < itemAttributesProto.size) {
                validationErrors.add(IllegalOrderItemAttributeSize(orderItem))
            }

            // 全てOk → Ok(OrderItemAttributes(value = attributes))
            // 一部でもErr → validationErrorsにエラー情報をadd. return Err(validationErrors)
            val (itemAttributes, itemAttributeValidationErrors) = itemAttributesProto.map {
                OrderItemAttribute.fromOrderCreationRequest(it)
            }.fold(Pair(mutableListOf<OrderItemAttribute>(), mutableListOf<ValidationError>())) { acc, result ->
                if (result.isOk) {
                    acc.first.add(result.get()!!)
                } else {
                    acc.second.addAll(result.getError()!!)
                }
                acc
            }

            if (itemAttributeValidationErrors.isNotEmpty()) {
                validationErrors.addAll(itemAttributeValidationErrors)
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