package com.example.orderprocessing.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackLevelTest {

    @Test
    fun test() {
        val sut = BlackLevel.fromString("low")
        assertThat(sut.isOk).isTrue()
    }
}