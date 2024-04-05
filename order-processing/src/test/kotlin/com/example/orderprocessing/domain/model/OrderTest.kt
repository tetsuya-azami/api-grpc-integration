package com.example.orderprocessing.domain.model

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.grpcinterface.proto.OrderOuterClass.OrderCreationRequest
import com.example.orderprocessing.helper.OrderTestHelper
import org.junit.jupiter.api.Test

class OrderTest {

    @Test
    fun test() {
        // Given
        val orderProto = OrderTestHelper.createTestOrderProto()
        // When
        val order = createTestOrder(orderProto)

        // Then
        OrderTestHelper.assert_作成された注文モデルが正しいこと(orderProto, order)
    }

    private fun createTestOrder(orderProto: OrderOuterClass.Order): Order {
        val orderValidationResult = Order.fromOrderCreationRequest(
            OrderCreationRequest.newBuilder()
                .setOrder(orderProto)
                .build()
        )
        when (orderValidationResult) {
            is Order.OrderValidationResult.Success -> {
                return orderValidationResult.order
            }

            is Order.OrderValidationResult.Failure -> {
                throw IllegalArgumentException("バリデーションエラーが発生しました。${orderValidationResult.validationErrors}")
            }
        }
    }
}