package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.orderprocessing.error.ValidationError

data class OrderItems private constructor(val value: List<OrderItem>) {

    companion object {
        private const val MINIMUM_ITEM_SIZE = 1
        private const val MAXIMUM_ITEM_SIZE = 100

        fun fromOrderCreationRequest(itemList: List<OrderOuterClass.Item>): OrderItemsValidationResult {
            val orderItemsValidationResults = itemList.map { OrderItem.fromOrderCreationRequest(it) }
            val (successes, failures) = orderItemsValidationResults.partition { it is OrderItem.OrderItemValidationResult.Success }

            val orderItems = successes
                .map { it as OrderItem.OrderItemValidationResult.Success }
                .map { it.orderItem }
            val validationErrors = failures
                .map { it as OrderItem.OrderItemValidationResult.Failure }
                .flatMap { it.validationErrors }
                .toMutableList()

            if (itemList.size < MINIMUM_ITEM_SIZE || MAXIMUM_ITEM_SIZE < itemList.size) {
                validationErrors.add(OrderItemsValidationError.IllegalItemSize)
            }

            if (validationErrors.isNotEmpty()) return OrderItemsValidationResult.Failure(validationErrors)

            return OrderItemsValidationResult.Success(OrderItems(orderItems))
        }
    }

    fun size(): Int {
        return value.size
    }

    sealed interface OrderItemsValidationResult {
        data class Success(val orderItems: OrderItems) : OrderItemsValidationResult
        data class Failure(val validationErrors: List<ValidationError>) : OrderItemsValidationResult
    }

    sealed interface OrderItemsValidationError {
        data object IllegalItemSize : ValidationError {
            override val message: String
                get() = "商品は${MINIMUM_ITEM_SIZE}個から${MAXIMUM_ITEM_SIZE}個の間で注文できます。"
        }
    }

    // TODO: Add a method to calculate the total price of the order items
}