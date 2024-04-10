package com.example.orderprocessing.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass.OrderCreationRequest
import com.example.grpcinterface.proto.OrderOuterClass.OrderCreationResponse
import com.example.orderprocessing.domain.model.OrderId
import com.example.orderprocessing.helper.OrderTestHelper
import com.example.orderprocessing.usecase.command.RegisterOrder
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class OrderControllerTest {

    @InjectMockKs
    private lateinit var orderController: OrderController

    @MockK
    private lateinit var registerOrder: RegisterOrder
    private lateinit var testOrderId: OrderId

    @BeforeEach
    fun setUp() {
        testOrderId = OrderId.new()
        every { registerOrder.execute(any()) } returns testOrderId
    }

    @Test
    fun 正常系() {
        // given
        val orderCreationRequest = OrderCreationRequest.newBuilder()
            .setOrder(
                OrderTestHelper.createOrderProto()
            )
            .build()

        runTest {
            // when
            val result = orderController.createOrder(orderCreationRequest)
            // then
            val expectedResponse = OrderCreationResponse.newBuilder()
                .setId(testOrderId.value)
                .build()
            assertThat(result).isEqualTo(expectedResponse)
        }
    }
}