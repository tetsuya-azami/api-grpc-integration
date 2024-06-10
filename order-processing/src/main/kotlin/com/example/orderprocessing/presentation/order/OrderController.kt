package com.example.orderprocessing.presentation.order

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.grpcinterface.proto.OrderServiceGrpcKt
import com.example.orderprocessing.usecase.command.RegisterOrder
import net.devh.boot.grpc.server.service.GrpcService
import org.slf4j.LoggerFactory
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZoneOffset

@GrpcService
@Suppress("unused")
class OrderController(
    private val registerOrder: RegisterOrder
) : OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun createOrder(request: OrderOuterClass.OrderCreationRequest): OrderOuterClass.OrderCreationResponse {
        val order = request.order
        val orderItemParams = order.orderItemsList.map { item ->
            val attributeParams = item.attributesList.map { attribute ->
                AttributeParam.new(
                    id = attribute.id,
                    name = attribute.name,
                    value = attribute.value,
                )
            }
            OrderItemParam.new(
                id = item.id,
                name = item.name,
                price = BigDecimal.valueOf(item.price.units),
                attributeParams = attributeParams,
                quantity = item.quantity,
            )
        }
        val orderParam = OrderParam.new(
            orderItemParams = orderItemParams,
            chainId = order.chain.id,
            shopId = order.shop.id,
            deliveryParam = DeliveryParam.new(
                deliveryType = order.delivery.type.name,
                addressId = order.delivery.addressId
            ),
            userParam = UserParam.new(id = order.user.id),
            paymentParam = PaymentParam.new(
                paymentMethod = order.payment.method.name,
                deliveryCharge = BigDecimal.valueOf(order.payment.deliveryCharge.units),
                nonTaxedTotalPrice = BigDecimal.valueOf(order.payment.nonTaxedTotalPrice.units),
                tax = BigDecimal.valueOf(order.payment.tax.units),
                taxedTotalPrice = BigDecimal.valueOf(order.payment.taxedTotalPrice.units)
            ),
            blackLevel = order.user.blackLevel.name,
            time = LocalDateTime.ofEpochSecond(order.time.seconds, order.time.nanos, ZoneOffset.of("+09:00"))
        )

        val orderId = registerOrder.execute(orderParam)

        return OrderOuterClass.OrderCreationResponse
            .newBuilder()
            .setId(orderId.value)
            .build()
            .let {
                logger.info("send response: $it")
                it
            }
    }
}