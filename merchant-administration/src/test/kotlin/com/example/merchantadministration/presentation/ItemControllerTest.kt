package com.example.merchantadministration.presentation

import com.example.grpcinterface.proto.CheckItemsWithSelectedAttributesRequestKt.itemWithAttributeIds
import com.example.grpcinterface.proto.checkItemsWithSelectedAttributesRequest
import com.example.merchantadministration.error.MerchantAdministrationIllegalArgumentException
import com.example.merchantadministration.usecase.query.ItemsWithSelectedAttributesCheckingQuery
import com.google.type.Money
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
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
            itemWithAttributeIds += itemWithAttributeIds {
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
            itemWithAttributeIds += itemWithAttributeIds {
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

    @Test
    fun パラメータ不正() {
        // given
        val request = checkItemsWithSelectedAttributesRequest {
            itemWithAttributeIds += itemWithAttributeIds {// itemIdおよびchainIdが設定されていない
                shopId = 1
                price = Money.newBuilder()
                    .setUnits(100)
                    .setCurrencyCode("JPY")
                    .build()
                selectedAttributeIds += 1
                selectedAttributeIds += 2
            }
            itemWithAttributeIds += itemWithAttributeIds {// shopIdおよびpriceが設定されていない
                itemId = 1
                chainId = 1
                selectedAttributeIds += 1
                selectedAttributeIds += 2
            }
        }

        // when
        val result = kotlin.runCatching { runTest { sut.checkItemsWithSelectedAttributes(request) } }

        // then
        assertThat(result.isFailure).isTrue()
        val exception =
            result.exceptionOrNull() as MerchantAdministrationIllegalArgumentException
        assertThat(exception.validationErrors).hasSize(4)
        assertThat(exception.validationErrors[0].field).isEqualTo("item")
        assertThat(exception.validationErrors[0].message).isEqualTo("商品IDがありません。")
        assertThat(exception.validationErrors[1].field).isEqualTo("chainId")
        assertThat(exception.validationErrors[1].message).isEqualTo("チェーンIDがありません。")
        assertThat(exception.validationErrors[2].field).isEqualTo("shopId")
        assertThat(exception.validationErrors[2].message).isEqualTo("店舗IDがありません。")
        assertThat(exception.validationErrors[3].field).isEqualTo("price")
        assertThat(exception.validationErrors[3].message).isEqualTo("商品価格がありません。")
        verify(exactly = 0) { mockedQueryService.checkItemsWithSelectedAttributes(any()) }
    }
}