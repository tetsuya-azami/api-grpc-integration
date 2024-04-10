package com.example.orderprocessing.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass.OrderCreationRequest
import com.example.orderprocessing.helper.OrderTestHelper
import com.example.orderprocessing.usecase.command.RegisterOrder
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class OrderControllerTest {

    private lateinit var orderController: OrderController

    @BeforeEach
    fun setUp() {
        val mockedRegisterOrder = mockk<RegisterOrder>()
        orderController = OrderController(mockedRegisterOrder)
    }

    @Test
    fun 正常系() {
        val orderCreationRequest = OrderCreationRequest.newBuilder()
            .setOrder(
                OrderTestHelper.createOrderProto()
            )
            .build()
        // TODO: write when, then
    }
}