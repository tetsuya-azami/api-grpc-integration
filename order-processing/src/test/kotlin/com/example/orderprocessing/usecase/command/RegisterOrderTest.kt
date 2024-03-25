package com.example.orderprocessing.usecase.command

import com.example.orderprocessing.helper.OrderTestHelper
import com.example.orderprocessing.repository.order.OrderRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class RegisterOrderTest {

    private val now = LocalDateTime.of(2000, 1, 2, 3, 4, 5)

    @BeforeEach
    fun setUp() {
        mockkStatic(LocalDateTime::class)
        every { LocalDateTime.now() } returns now
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun test() {
        // Given
        val mockedOrderRepository = mockk<OrderRepository>()
        val order = OrderTestHelper.createTestOrder()
        every { mockedOrderRepository.registerOrder(any(), any()) } returns order.orderId
        val sut = RegisterOrder(mockedOrderRepository)

        // When
        val actualOrderId = sut.execute(order)

        // Then
        assertThat(actualOrderId).isEqualTo(order.orderId)
    }
}