package com.example.orderprocessing.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass.OrderCreationRequest
import com.example.grpcinterface.proto.OrderOuterClass.OrderCreationResponse
import com.example.orderprocessing.domain.model.OrderId
import com.example.orderprocessing.helper.OrderTestHelper
import com.example.orderprocessing.usecase.command.RegisterOrder
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OrderControllerTest {

    private lateinit var orderController: OrderController
    private lateinit var testOrderId: OrderId

    @BeforeEach
    fun setUp() {
        testOrderId = OrderId.new()
        val mockedRegisterOrder = mockk<RegisterOrder>()
        every { mockedRegisterOrder.execute(any()) } returns testOrderId

        orderController = OrderController(mockedRegisterOrder)
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