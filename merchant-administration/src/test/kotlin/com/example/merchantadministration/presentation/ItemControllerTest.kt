package com.example.merchantadministration.presentation

import com.example.grpcinterface.proto.CheckItemsWithSelectedAttributesRequestKt.itemWithAttributeIds
import com.example.grpcinterface.proto.checkItemsWithSelectedAttributesRequest
import com.example.merchantadministration.usecase.query.ItemsWithSelectedAttributesCheckingQuery
import com.google.type.Money
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ItemControllerTest {
    private lateinit var sut: ItemController
    private lateinit var mockedQueryService: ItemsWithSelectedAttributesCheckingQuery

    @BeforeEach
    fun setUp() {
        mockedQueryService = mockk<ItemsWithSelectedAttributesCheckingQuery>()
        sut = ItemController(mockedQueryService)
    }

    @Test
    fun 正常系() {
        // given
        val request = checkItemsWithSelectedAttributesRequest {
            itemWithAttributeIds {
                itemId = 1
                chainId = 1
                shopId = 1
                price = Money.newBuilder()
                    .setUnits(100)
                    .setCurrencyCode("JPY")
                    .build()
                selectedAttributeIds += 1
                selectedAttributeIds += 2
            }
            itemWithAttributeIds {
                itemId = 1
                chainId = 1
                shopId = 1
                price = Money.newBuilder()
                    .setUnits(100)
                    .setCurrencyCode("JPY")
                    .build()
                selectedAttributeIds += 1
                selectedAttributeIds += 2
            }
        }

        every { mockedQueryService.checkItemsWithSelectedAttributes(any()) } returns true

        runTest {
            // when
            val actual = sut.checkItemsWithSelectedAttributes(request)
            // then
            assertThat(actual.resultCode).isEqualTo(0)
        }
    }
}