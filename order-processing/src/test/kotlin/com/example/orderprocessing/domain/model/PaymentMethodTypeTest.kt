package com.example.orderprocessing.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PaymentMethodTypeTest {
    @Test
    fun インスタンス化_正常系() {
        val sut = PaymentMethodType.fromString("cash")
        assertThat(sut.isOk).isTrue()
    }
}