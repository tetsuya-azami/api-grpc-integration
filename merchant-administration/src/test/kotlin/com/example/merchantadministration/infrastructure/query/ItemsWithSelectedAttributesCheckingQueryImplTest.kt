package com.example.merchantadministration.infrastructure.query

import com.example.merchantadministration.presentation.ItemWithSelectedAttributeIdsParam
import com.example.merchantadministration.presentation.SelectedAttributeIdParam
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ItemsWithSelectedAttributesCheckingQueryImplTest {
    @Test
    fun 正常系() {
        // Given
        val sut = ItemsWithSelectedAttributesCheckingQueryImpl()
        val param = ItemWithSelectedAttributeIdsParam.new(
            itemId = 1,
            chainId = 2,
            shopId = 3,
            price = BigDecimal(100),
            selectedAttributeIds = listOf(
                SelectedAttributeIdParam.new(4), SelectedAttributeIdParam.new(5)
            )
        )
        // When
        assertThat(sut.checkItemsWithSelectedAttributes(param)).isTrue()
        // Then
    }
}