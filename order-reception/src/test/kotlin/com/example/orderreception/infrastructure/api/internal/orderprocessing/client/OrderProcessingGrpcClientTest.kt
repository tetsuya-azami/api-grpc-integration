package com.example.orderreception.infrastructure.api.internal.orderprocessing.client

import com.example.grpcinterface.proto.OrderServiceGrpc
import com.example.orderreception.domain.model.order.*
import com.example.orderreception.infrastructure.entity.custom.ItemWithAttributesBase
import com.example.orderreception.infrastructure.entity.generated.AttributesBase
import com.example.orderreception.infrastructure.entity.generated.UsersBase
import com.example.orderreception.presentation.order.*
import io.grpc.ManagedChannel
import io.grpc.Server
import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.inprocess.InProcessServerBuilder
import io.grpc.testing.GrpcCleanupRule
import io.grpc.util.MutableHandlerRegistry
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
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

    @BeforeEach
    fun setUp() {
        serviceRegistry = MutableHandlerRegistry()
        testServerName = InProcessServerBuilder.generateName()
        inProcessServers = grpcCleanup.register(
            InProcessServerBuilder
                .forName(testServerName)
                .directExecutor()
                .fallbackHandlerRegistry(serviceRegistry)
                .build()
                .start()
        )
        inProcessChannel = grpcCleanup.register(
            InProcessChannelBuilder.forName(testServerName)
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

        val orderParam = getOrderParam()
        val orderItems = orderParam.orderItemParams.mapIndexed { index, orderItemParam ->
            val attributes = orderItemParam.attributes.mapIndexed { index, attributeParam ->
                AttributesBase(
                    attributeId = attributeParam.attributeId,
                    name = "属性名$index",
                    value = "属性値$index"
                )
            }
            val itemWithAttributesBase = ItemWithAttributesBase(
                itemId = orderItemParam.itemId,
                name = "テスト商品$index",
                price = orderItemParam.price,
                attributes = attributes
            )
            OrderItem.fromBaseAndParam(itemWithAttributesBase = itemWithAttributesBase, orderItemParam = orderItemParam)
        }

        val sut = OrderProcessingGrpcClient(OrderServiceGrpc.newBlockingStub(inProcessChannel))

        // when
        val result = sut.registerOrder(
            orderParam = orderParam,
            orderItems = orderItems,
            user = User.fromBase(
                UsersBase(userId = 1L, blackLevel = BlackLevel.LOW.code)
            )
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
            assertThat(it.order.itemsList[0].attributesList[0].name).isEqualTo("属性名0")
            assertThat(it.order.itemsList[0].attributesList[0].value).isEqualTo("属性値0")
            assertThat(it.order.itemsList[0].attributesList[1].id).isEqualTo(2)
            assertThat(it.order.itemsList[0].attributesList[1].name).isEqualTo("属性名1")
            assertThat(it.order.itemsList[0].attributesList[1].value).isEqualTo("属性値1")
            assertThat(it.order.itemsList[0].quantity).isEqualTo(1)
            assertThat(it.order.itemsList[1].id).isEqualTo(2)
            assertThat(it.order.itemsList[1].price.units).isEqualTo(200)
            assertThat(it.order.itemsList[1].price.nanos).isEqualTo(0)
            assertThat(it.order.itemsList[1].price.currencyCode).isEqualTo("JPY")
            assertThat(it.order.itemsList[1].attributesList).hasSize(2)
            assertThat(it.order.itemsList[1].attributesList[0].id).isEqualTo(3)
            assertThat(it.order.itemsList[1].attributesList[0].name).isEqualTo("属性名0")
            assertThat(it.order.itemsList[1].attributesList[0].value).isEqualTo("属性値0")
            assertThat(it.order.itemsList[1].attributesList[1].id).isEqualTo(4)
            assertThat(it.order.itemsList[1].attributesList[1].name).isEqualTo("属性名1")
            assertThat(it.order.itemsList[1].attributesList[1].value).isEqualTo("属性値1")
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
    }

    private fun getOrderParam(): OrderParam {
        val orderItemParams = listOf(
            OrderItemParam(
                itemId = 1,
                price = BigDecimal.valueOf(100),
                attributes = listOf(AttributeParam(1), AttributeParam(2)),
                quantity = 1,
            ),
            OrderItemParam(
                itemId = 2,
                price = BigDecimal.valueOf(200),
                attributes = listOf(AttributeParam(3), AttributeParam(4)),
                quantity = 2,
            )
        )

        return OrderParam(
            orderItemParams = orderItemParams,
            chainId = 1,
            shopId = 1,
            deliveryParam = DeliveryParam(DeliveryType.IMMEDIATE, 1),
            userId = 1,
            paymentParam = PaymentParam(
                paymentMethod = PaymentMethodType.PAYPAY,
                deliveryCharge = BigDecimal.valueOf(350),
                nonTaxedTotalPrice = BigDecimal.valueOf(850),
                tax = BigDecimal.valueOf(85),
                taxedTotalPrice = BigDecimal.valueOf(935)
            ),
            time = now,
        )
    }
}