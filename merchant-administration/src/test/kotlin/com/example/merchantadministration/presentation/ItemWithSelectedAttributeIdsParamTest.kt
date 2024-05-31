package com.example.merchantadministration.presentation

import com.example.merchantadministration.error.MerchantAdministrationIllegalArgumentException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class ItemWithSelectedAttributeIdsParamTest {
    @Test
    fun インスタンス化_正常系() {
        val itemId = 1L
        val chainId = 2L
        val shopId = 3L
        val selectedAttributeIds = listOf(
            SelectedAttributeIdParam.new(4L), SelectedAttributeIdParam.new(5L)
        )
        val actual = ItemWithSelectedAttributeIdsParam.new(itemId, chainId, shopId, selectedAttributeIds)

        assertThat(actual.itemId).isEqualTo(itemId)
        assertThat(actual.chainId).isEqualTo(chainId)
        assertThat(actual.shopId).isEqualTo(shopId)
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
        selectedAttributeIds: List<SelectedAttributeIdParam>?,
        expectedMessage: String
    ) {
        val actual = runCatching {
            ItemWithSelectedAttributeIdsParam.new(itemId, chainId, shopId, selectedAttributeIds)
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
            arrayOf(null, 2L, 3L, listOf(SelectedAttributeIdParam.new(4L)), "商品IDがありません。"),
            arrayOf(1L, null, 3L, listOf(SelectedAttributeIdParam.new(4L)), "チェーンIDがありません。"),
            arrayOf(1L, 2L, null, listOf(SelectedAttributeIdParam.new(4L)), "店舗IDがありません。"),
            arrayOf(1L, 2L, 3L, null, "商品属性情報がありません。"),
            arrayOf(1L, 2L, 3L, emptyList<SelectedAttributeIdParam>(), "商品属性情報がありません。")
        )
    }
}
