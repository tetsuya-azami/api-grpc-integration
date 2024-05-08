package com.example.orderreception.infrastructure.api.internal.orderprocessing.client

import com.example.grpcinterface.proto.OrderServiceGrpc
import com.example.orderreception.domain.model.order.*
import com.example.orderreception.infrastructure.entity.custom.ItemWithAttributesBase
import com.example.orderreception.infrastructure.entity.generated.AttributesBase
import com.example.orderreception.infrastructure.entity.generated.UsersBase
import com.example.orderreception.presentation.order.*
import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.inprocess.InProcessServerBuilder
import io.grpc.testing.GrpcCleanupRule
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.LocalDateTime

@SpringBootTest
class OrderProcessingGrpcClientTest {
    companion object {
        private val now = LocalDateTime.of(2000, 1, 2, 3, 4, 5)
    }

    @get:Rule
    val grpcCleanup = GrpcCleanupRule()

    @Test
    fun 正常系() {
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

        val serverName = InProcessServerBuilder.generateName()
        grpcCleanup.register(
            InProcessServerBuilder
                .forName(serverName)
                .directExecutor()
                // TODO: 内部処理を差し替えられるようにする(異常系の実施をするため)
                .addService(OrderProcessingServerImplForIntegrationTest())
                .build()
                .start()
        )
        val channel = grpcCleanup.register(
            InProcessChannelBuilder.forName(serverName)
                .directExecutor()
                .build()
        )
        val sut = OrderProcessingGrpcClient(OrderServiceGrpc.newBlockingStub(channel))

        val result = sut.registerOrder(
            orderParam = orderParam,
            orderItems = orderItems,
            user = User.fromBase(
                UsersBase(userId = 1L, blackLevel = BlackLevel.LOW.code)
            )
        )

        assertThat(result.orderId).isEqualTo("testId")
    }

    private fun setUpInProcessServer() {

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