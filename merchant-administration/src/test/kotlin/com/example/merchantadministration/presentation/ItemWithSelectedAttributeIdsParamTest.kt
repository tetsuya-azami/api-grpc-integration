package com.example.merchantadministration.presentation

import com.example.merchantadministration.error.MerchantAdministrationIllegalArgumentException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigDecimal

class ItemWithSelectedAttributeIdsParamTest {
    @Test
    fun インスタンス化_正常系() {
        val itemId = 1L
        val chainId = 2L
        val shopId = 3L
        val price = BigDecimal(100)
        val selectedAttributeIds = listOf(
            SelectedAttributeIdParam.new(4L), SelectedAttributeIdParam.new(5L)
        )
        val actual = ItemWithSelectedAttributeIdsParam.new(itemId, chainId, shopId, price, selectedAttributeIds).value

        assertThat(actual.itemId).isEqualTo(itemId)
        assertThat(actual.chainId).isEqualTo(chainId)
        assertThat(actual.shopId).isEqualTo(shopId)
        assertThat(actual.price).isEqualTo(price)
        actual.selectedAttributeIds.forEachIndexed { index, selectedAttributeIdParam ->
            assertThat(selectedAttributeIdParam.attributeId).isEqualTo(selectedAttributeIds[index].attributeId)
        }
    }

    @ParameterizedTest
    @MethodSource("illegalParams")
    fun インスタンス化_異常系(
        itemId: Long?,
        chainId: Long?,
        shopId: Long?,
        price: BigDecimal?,
        selectedAttributeIds: List<SelectedAttributeIdParam>?,
        expectedMessage: String
    ) {
        val actual = runCatching {
            ItemWithSelectedAttributeIdsParam.new(itemId, chainId, shopId, price, selectedAttributeIds)
        }

        assertThat(actual.isFailure).isTrue
        assertThat(actual.exceptionOrNull()).isInstanceOf(MerchantAdministrationIllegalArgumentException::class.java)
        val exception = actual.exceptionOrNull() as MerchantAdministrationIllegalArgumentException
        assertThat(exception.validationErrors.size).isEqualTo(1)
        assertThat(exception.validationErrors.first().message).isEqualTo(expectedMessage)
    }

    companion object {
        @JvmStatic
        private fun illegalParams() = listOf(
            arrayOf(
                null,
                2L,
                3L,
                BigDecimal.valueOf(100L),
                listOf(SelectedAttributeIdParam.new(4L)),
                "商品IDがありません。"
            ),
            arrayOf(
                1L,
                null,
                3L,
                BigDecimal.valueOf(100L),
                listOf(SelectedAttributeIdParam.new(4L)),
                "チェーンIDがありません。"
            ),
            arrayOf(
                1L,
                2L,
                null,
                BigDecimal.valueOf(100L),
                listOf(SelectedAttributeIdParam.new(4L)),
                "店舗IDがありません。"
            ),
            arrayOf(
                1L,
                2L,
                3L,
                null,
                listOf(SelectedAttributeIdParam.new(4L)),
                "商品価格がありません。"
            ),
            arrayOf(
                1L,
                2L,
                3L,
                BigDecimal.valueOf(100L),
                null,
                "商品属性情報がありません。"
            ),
            arrayOf(
                1L,
                2L,
                3L,
                BigDecimal.valueOf(100L),
                emptyList<SelectedAttributeIdParam>(),
                "商品属性情報がありません。"
            )
        )
    }
}
