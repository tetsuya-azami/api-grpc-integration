package com.example.orderprocessing.domain.model

import com.github.michaelbull.result.get
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OrderItemAttributesTest {

    @Test
    fun インスタンス化_正常系() {
        val actual = OrderItemAttributes.new(
            itemId = 1,
            value = listOf(
                OrderItemAttribute.new(
                    itemId = 1,
                    attributeId = 1,
                    name = "テスト属性名1",
                    value = "テスト属性値1",
                ),
                OrderItemAttribute.new(
                    itemId = 1,
                    attributeId = 2,
                    name = "テスト属性名2",
                    value = "テスト属性値2",
                )
            )
        ).get()!!

        assertThat(actual.itemId).isEqualTo(1)
        assertThat(actual.value.size).isEqualTo(2)
        assertThat(actual.value[0].itemId).isEqualTo(1)
        assertThat(actual.value[0].attributeId).isEqualTo(1)
        assertThat(actual.value[0].name).isEqualTo("テスト属性名1")
        assertThat(actual.value[0].value).isEqualTo("テスト属性値1")
        assertThat(actual.value[1].itemId).isEqualTo(1)
        assertThat(actual.value[1].attributeId).isEqualTo(2)
        assertThat(actual.value[1].name).isEqualTo("テスト属性名2")
        assertThat(actual.value[1].value).isEqualTo("テスト属性値2")
    }
}