package com.example.orderreception.infrastructure.api.internal.orderprocessing.client

import com.example.grpcinterface.proto.OrderOuterClass.OrderCreationRequest
import com.example.grpcinterface.proto.OrderOuterClass.OrderCreationResponse
import com.example.grpcinterface.proto.OrderServiceGrpc
import com.example.orderreception.domain.model.order.BlackLevel
import com.example.orderreception.domain.model.order.Delivery.DeliveryType
import com.example.orderreception.domain.model.order.Payment.PaymentMethodType
import com.example.orderreception.domain.model.order.User
import com.example.orderreception.helper.OrderTestHelper
import com.example.orderreception.infrastructure.api.interceptor.LoggingInterceptor
import io.grpc.ManagedChannel
import io.grpc.Metadata
import io.grpc.Server
import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.inprocess.InProcessServerBuilder
import io.grpc.testing.GrpcCleanupRule
import io.grpc.util.MutableHandlerRegistry
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.time.ZoneOffset

@SpringBootTest
class OrderProcessingGrpcClientTest {
    companion object {
        private val now = LocalDateTime.of(2000, 1, 2, 3, 4, 5)
    }

    @get:Rule
    val grpcCleanup = GrpcCleanupRule()
    private lateinit var serviceRegistry: MutableHandlerRegistry
    private lateinit var testServerName: String
    private lateinit var inProcessServers: Server
    private lateinit var inProcessChannel: ManagedChannel
    private val metadataCaptor = slot<io.grpc.Metadata>()
    private val mockInterceptor = spyk<TestInterceptor>()

    @BeforeEach
    fun setUp() {
        serviceRegistry = MutableHandlerRegistry()
        testServerName = InProcessServerBuilder.generateName()
        inProcessServers = grpcCleanup.register(
            InProcessServerBuilder
                .forName(testServerName)
                .directExecutor()
                .fallbackHandlerRegistry(serviceRegistry)
                .intercept(mockInterceptor)
                .build()
                .start()
        )
        inProcessChannel = grpcCleanup.register(
            InProcessChannelBuilder
                .forName(testServerName)
                .intercept(LoggingInterceptor())
                .directExecutor()
                .build()
        )
    }

    @AfterEach
    fun tearDown() {
        inProcessServers.shutdownNow()
        inProcessChannel.shutdownNow()
    }

    @Test
    fun 正常系() {
        // given
        val targetServer = OrderProcessingServerImplForIntegrationTest()
        serviceRegistry.addService(targetServer)
        val order = OrderTestHelper.getTestInstance()
        val sut = OrderProcessingGrpcClient(OrderServiceGrpc.newBlockingStub(inProcessChannel))

        // when
        val result = sut.registerOrder(
            order = order,
            user = User.reconstruct(id = 1L, blackLevel = BlackLevel.LOW.code)
        )

        // then
        assertThat(result.orderId).isEqualTo("testId")
        // リクエスト内容の検証
        targetServer.receivedOrderCreationRequest.let {
            assertThat(it.order.itemsList).hasSize(2)
            assertThat(it.order.itemsList[0].id).isEqualTo(1)
            assertThat(it.order.itemsList[0].price.units).isEqualTo(100)
            assertThat(it.order.itemsList[0].price.nanos).isEqualTo(0)
            assertThat(it.order.itemsList[0].price.currencyCode).isEqualTo("JPY")
            assertThat(it.order.itemsList[0].attributesList).hasSize(2)
            assertThat(it.order.itemsList[0].attributesList[0].id).isEqualTo(1)
            assertThat(it.order.itemsList[0].attributesList[0].name).isEqualTo(order.orderItems[0].attributes[0].name)
            assertThat(it.order.itemsList[0].attributesList[0].value).isEqualTo(order.orderItems[0].attributes[0].value)
            assertThat(it.order.itemsList[0].attributesList[1].id).isEqualTo(2)
            assertThat(it.order.itemsList[0].attributesList[1].name).isEqualTo(order.orderItems[0].attributes[1].name)
            assertThat(it.order.itemsList[0].attributesList[1].value).isEqualTo(order.orderItems[0].attributes[1].value)
            assertThat(it.order.itemsList[0].quantity).isEqualTo(1)
            assertThat(it.order.itemsList[1].id).isEqualTo(2)
            assertThat(it.order.itemsList[1].price.units).isEqualTo(200)
            assertThat(it.order.itemsList[1].price.nanos).isEqualTo(0)
            assertThat(it.order.itemsList[1].price.currencyCode).isEqualTo("JPY")
            assertThat(it.order.itemsList[1].attributesList).hasSize(2)
            assertThat(it.order.itemsList[1].attributesList[0].id).isEqualTo(3)
            assertThat(it.order.itemsList[1].attributesList[0].name).isEqualTo(order.orderItems[1].attributes[0].name)
            assertThat(it.order.itemsList[1].attributesList[0].value).isEqualTo(order.orderItems[1].attributes[0].value)
            assertThat(it.order.itemsList[1].attributesList[1].id).isEqualTo(4)
            assertThat(it.order.itemsList[1].attributesList[1].name).isEqualTo(order.orderItems[1].attributes[1].name)
            assertThat(it.order.itemsList[1].attributesList[1].value).isEqualTo(order.orderItems[1].attributes[1].value)
            assertThat(it.order.itemsList[1].quantity).isEqualTo(2)
            assertThat(it.order.chain.id).isEqualTo(1)
            assertThat(it.order.shop.id).isEqualTo(1)
            assertThat(it.order.delivery.type.name).isEqualTo(DeliveryType.IMMEDIATE.name)
            assertThat(it.order.delivery.addressId).isEqualTo(1)
            assertThat(it.order.user.id).isEqualTo(1)
            assertThat(it.order.user.blackLevel.name).isEqualTo(BlackLevel.LOW.name)
            assertThat(it.order.payment.paymentMethod.name).isEqualTo(PaymentMethodType.PAYPAY.name)
            assertThat(it.order.payment.deliveryCharge).isEqualTo(350)
            assertThat(it.order.payment.nonTaxedTotalPrice).isEqualTo(850)
            assertThat(it.order.payment.tax).isEqualTo(85)
            assertThat(it.order.payment.taxedTotalPrice).isEqualTo(935)
            assertThat(it.order.time.seconds).isEqualTo(now.toEpochSecond(ZoneOffset.of("+09:00")))
            assertThat(it.order.time.nanos).isEqualTo(0)
        }

        // リクエストヘッダの検証
        verify {
            mockInterceptor.interceptCall<OrderCreationRequest, OrderCreationResponse>(
                any(),
                capture(metadataCaptor),
                any()
            )
        }
        metadataCaptor.captured.let {
            assertThat(
                it.get(Metadata.Key.of("REQUEST_COMPONENT_NAME", Metadata.ASCII_STRING_MARSHALLER))
            ).isEqualTo("order-reception")
        }
    }
}