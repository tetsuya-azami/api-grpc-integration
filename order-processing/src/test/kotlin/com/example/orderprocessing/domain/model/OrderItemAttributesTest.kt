package com.example.orderprocessing.domain.model

import com.example.orderprocessing.helper.OrderTestHelper
import com.github.michaelbull.result.get
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class OrderItemAttributesTest {

    @Test
    fun インスタンス化_正常系() {
        val testOrderItemParam = OrderTestHelper.createOrderItemParam(price = BigDecimal.valueOf(100), quantity = 1)
        val actualOrderItemAttributes = OrderItemAttributes.fromParam(testOrderItemParam).get()

        for ((index, actual) in actualOrderItemAttributes!!.value.withIndex()) {
            val expected = testOrderItemParam.attributeParams[index]

            assertThat(actual.attributeId).isEqualTo(expected.id)
            assertThat(actual.name).isEqualTo(expected.name)
            assertThat(actual.value).isEqualTo(expected.value)
        }
    }
}