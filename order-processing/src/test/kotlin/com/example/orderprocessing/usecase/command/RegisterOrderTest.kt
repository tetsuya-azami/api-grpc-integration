package com.example.orderprocessing.usecase.command

import com.example.orderprocessing.domain.model.OrderId
import com.example.orderprocessing.helper.OrderTestHelper
import com.example.orderprocessing.infrastructure.order.OrderRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.isAccessible

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
        val orderParam = OrderTestHelper.createOrderParam()
        val mockedOrderRepository = mockk<OrderRepository>()
        val primaryConstructor = OrderId::class.primaryConstructor
        primaryConstructor!!.isAccessible = true
        val orderId = primaryConstructor.call("1")
        every { mockedOrderRepository.registerOrder(any(), any()) } returns orderId
        val sut = RegisterOrder(mockedOrderRepository)

        // When
        val actualOrderId = sut.execute(orderParam)

        // Then
        assertThat(actualOrderId).isEqualTo(orderId)
    }
}