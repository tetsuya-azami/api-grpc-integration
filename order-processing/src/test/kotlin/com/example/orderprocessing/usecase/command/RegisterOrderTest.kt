package com.example.orderprocessing.usecase.command

import com.example.orderprocessing.domain.model.OrderId
import com.example.orderprocessing.error.exception.OrderProcessingIllegalArgumentException
import com.example.orderprocessing.helper.OrderTestHelper
import com.example.orderprocessing.infrastructure.repository.order.OrderRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockkStatic
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class RegisterOrderTest {

    @InjectMockKs
    private lateinit var sut: RegisterOrder

    @MockK
    private lateinit var orderRepository: OrderRepository
    private lateinit var testOrderId: OrderId
    private val now = LocalDateTime.of(2000, 1, 2, 3, 4, 5)

    @BeforeEach
    fun setUp() {
        mockkStatic(LocalDateTime::class)
        every { LocalDateTime.now() } returns now

        testOrderId = OrderId.new()
        every { orderRepository.registerOrder(any(), any()) } returns testOrderId
    }

    @Test
    fun 正常系() {
        // Given
        val orderParam = OrderTestHelper.createOrderParam()

        // When
        val actualOrderId = sut.execute(orderParam)

        // Then
        assertThat(actualOrderId).isEqualTo(testOrderId)
        verify(exactly = 1) { orderRepository.registerOrder(any(), now) }
    }

    @Test
    fun 異常系_注文商品がない() {
        // Given
        val orderParam = OrderTestHelper.createOrderParam(
            itemParams = emptyList(),
            blackLevel = "illegalBlackLevel"
        )

        // When
        val actual = kotlin.runCatching { sut.execute(orderParam) }

        // Then
        val exception =
            actual.exceptionOrNull() as OrderProcessingIllegalArgumentException
        assertThat(exception.validationErrors[0].fieldName).isEqualTo("orderItems")
        assertThat(exception.validationErrors[0].description).isEqualTo("商品は1個から100個の間で注文できます。")
        verify(exactly = 0) { orderRepository.registerOrder(any(), now) }
    }
}