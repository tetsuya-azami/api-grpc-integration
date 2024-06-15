package com.example.orderprocessing.domain.model

import com.github.michaelbull.result.get
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class OrderItemsTest {
    @Test
    fun インスタンス化_正常系() {
        // Given & When
        val orderItemsValue = listOf(
            getOrderItem(),
            getOrderItem(itemId = 2, quantity = 2, price = BigDecimal.valueOf(200))
        )
        val orderItems = OrderItems.new(value = orderItemsValue).get()!!
        // Then
        assertThat(orderItems.size()).isEqualTo(2)
        assertThat(orderItems.value[0].itemId).isEqualTo(1)
        assertThat(orderItems.value[0].quantity).isEqualTo(1)
        assertThat(orderItems.value[0].price).isEqualTo(100.toBigDecimal())
        assertThat(orderItems.value[1].itemId).isEqualTo(2)
        assertThat(orderItems.value[1].quantity).isEqualTo(2)
        assertThat(orderItems.value[1].price).isEqualTo(200.toBigDecimal())
    }

    @Test
    fun インスタンス化_注文商品が0件() {
        // Given & When
        val orderItemsValue = emptyList<OrderItem>()
        val actual = OrderItems.new(value = orderItemsValue)
        // Then
        assertThat(actual.isErr).isTrue()
        assertThat(actual.error).hasSize(1)
        assertThat(actual.error[0].fieldName).isEqualTo("orderItems")
        assertThat(actual.error[0].description).isEqualTo("商品は1個から100個の間で注文できます。")
    }

    @Test
    fun インスタンス化_注文商品が101件() {
        // Given & When
        val orderItemsValue = List(101) { getOrderItem(itemId = it.toLong()) }
        val actual = OrderItems.new(value = orderItemsValue)
        // Then
        assertThat(actual.isErr).isTrue()
        assertThat(actual.error).hasSize(1)
        assertThat(actual.error[0].fieldName).isEqualTo("orderItems")
        assertThat(actual.error[0].description).isEqualTo("商品は1個から100個の間で注文できます。")
    }

    private fun getOrderItem(
        itemId: Long = 1,
        quantity: Int = 1,
        orderItemAttributes: OrderItemAttributes = OrderItemAttributes.new(
            itemId = itemId,
            value = listOf()
        ).get()!!,
        price: BigDecimal = BigDecimal.valueOf(100)
    ) = OrderItem.new(
        itemId = itemId,
        quantity = quantity,
        orderItemAttributes = orderItemAttributes,
        price = price
    ).get()!!
}
