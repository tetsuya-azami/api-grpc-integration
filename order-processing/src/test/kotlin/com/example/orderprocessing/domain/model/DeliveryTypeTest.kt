package com.example.orderprocessing.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeliveryTypeTest {

    @Test
    fun インスタンス化_正常系() {
        val sut = DeliveryType.fromString("IMMEDIATE")
        assertThat(sut.isOk).isTrue()
    }
}
