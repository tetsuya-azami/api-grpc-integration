package com.example.orderprocessing.domain.model

import com.example.orderprocessing.helper.OrderTestHelper
import com.github.michaelbull.result.get
import org.junit.jupiter.api.Test

class OrderTest {

    @Test
    fun test() {
        // Given
        val orderParam = OrderTestHelper.createOrderParam()
        // When
        val order = Order.fromParam(orderParam = orderParam)

        // Then
        OrderTestHelper.assert_作成された注文モデルが正しいこと(orderParam, order.get()!!)
    }
}