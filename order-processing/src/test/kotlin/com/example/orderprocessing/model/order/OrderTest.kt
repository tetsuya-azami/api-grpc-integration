package com.example.orderprocessing.model.order

import com.example.grpcinterface.proto.OrderOuterClass.OrderCreationRequest
import com.example.orderprocessing.helper.OrderTestHelper
import org.junit.jupiter.api.Test

class OrderTest {

    @Test
    fun test() {
        // Given
        val orderProto = OrderTestHelper.createTestOrderProto()
        // When
        val order = Order.fromOrderCreationRequest(
            OrderCreationRequest.newBuilder()
                .setOrder(orderProto)
                .build()
        )
        // Then
        OrderTestHelper.assert_作成された注文モデルが正しいこと(orderProto, order)
    }
}