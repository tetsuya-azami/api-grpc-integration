package com.example.orderprocessing.domain.model

import com.github.michaelbull.result.get
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class BlackLevelTest {
    companion object {
        @JvmStatic
        private fun successCaseProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("low", BlackLevel.LOW),
                Arguments.arguments("Low", BlackLevel.LOW),
                Arguments.arguments("LOW", BlackLevel.LOW),
                Arguments.arguments("middle", BlackLevel.MIDDLE),
                Arguments.arguments("high", BlackLevel.HIGH),
            )
        }
    }

    @ParameterizedTest
    @MethodSource("successCaseProvider")
    fun インスタンス化_正常系(value: String, expectedBlackLevel: BlackLevel) {
        val sut = BlackLevel.fromString(value)
        assertThat(sut.isOk).isTrue()
        assertThat(sut.get()).isEqualTo(expectedBlackLevel)
    }

    @Test
    fun インスタンス化_不正なブラックレベルの時() {
        val illegalBlackLevel = "illegalBlackLevel"
        val sut = BlackLevel.fromString(illegalBlackLevel)
        assertThat(sut.isErr).isTrue()
        assertThat(sut.error[0].description).isEqualTo("ブラックレベルは${BlackLevel.entries}の中から選んでください。ブラックレベル: $illegalBlackLevel")
    }
}