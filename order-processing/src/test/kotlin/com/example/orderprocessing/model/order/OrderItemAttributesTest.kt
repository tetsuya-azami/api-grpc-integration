package com.example.orderprocessing.model.order

import com.example.orderprocessing.helper.OrderTestHelper
import org.junit.jupiter.api.Test

class OrderItemAttributesTest {

    @Test
    fun test() {
        val testOrderItems = OrderTestHelper.createTestOrderItem(
            price = 100,
            itemId = 1,
            name = "テスト商品",
            quantity = 2,
            attributes = listOf(3, 4, 5)
        )
        OrderItemAttributes.fromOrderCreationRequest(testOrderItems)
    }
}