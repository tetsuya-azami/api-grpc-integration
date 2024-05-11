package com.example.orderreception.usecase

import com.example.orderreception.domain.model.order.Delivery
import com.example.orderreception.domain.model.order.Delivery.DeliveryType
import com.example.orderreception.domain.model.order.Order
import com.example.orderreception.domain.model.order.Payment
import com.example.orderreception.error.ValidationError
import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException
import com.example.orderreception.infrastructure.api.internal.orderprocessing.client.OrderProcessingGrpcClient
import com.example.orderreception.presentation.order.OrderParam
import com.example.orderreception.usecase.query.AddressQuery
import com.example.orderreception.usecase.query.OrderItemFactory
import com.example.orderreception.usecase.query.UserQuery
import com.github.michaelbull.result.getOrElse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RegisterOrder(
    val userQuery: UserQuery,
    val addressQuery: AddressQuery,
    val orderItemFactory: OrderItemFactory,
    val orderProcessingGrpcClient: OrderProcessingGrpcClient
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    fun execute(orderParam: OrderParam): String {
        val user =
            userQuery.findById(orderParam.userId)
                ?: run {
                    logger.warn("存在しないユーザです。userId: ${orderParam.userId}")
                    throw OrderReceptionIllegalArgumentException(
                        validationErrors = listOf(
                            ValidationError(
                                field = "user",
                                message = "存在しないユーザです。userId: ${orderParam.userId}"
                            )
                        )
                    )
                }

        if (user.isBlackUser()) {
            logger.warn("ブラックリストユーザです。userId: ${orderParam.userId}")
            throw OrderReceptionIllegalArgumentException(
                validationErrors = listOf(
                    ValidationError(
                        field = "user",
                        message = "ブラックリストユーザです。userId: ${orderParam.userId}"
                    )
                )
            )
        }

        val address = addressQuery.findByAddressIdAndUserId(orderParam.deliveryParam.addressId, orderParam.userId)
            ?: kotlin.run {
                logger.warn("存在しない配達先住所です。userId: ${orderParam.userId}, addressId: ${orderParam.deliveryParam.addressId}")
                throw OrderReceptionIllegalArgumentException(
                    validationErrors = listOf(
                        ValidationError(
                            field = "delivery",
                            message = "存在しない配達先住所です。userId: ${orderParam.userId}, addressId: ${orderParam.deliveryParam.addressId}"
                        )
                    )
                )
            }

        // マスタデータを元に商品、属性情報を作成する。
        val orderItems = orderItemFactory.createOrderItems(
            orderItemParams = orderParam.orderItemParams,
            chainId = orderParam.chainId,
            shopId = orderParam.shopId
        )

        val deliveryType =
            DeliveryType.fromString(orderParam.deliveryParam.deliveryType)
                .getOrElse { errors ->
                    logger.warn("$errors")
                    throw OrderReceptionIllegalArgumentException(validationErrors = errors)
                }

        val delivery = Delivery.new(
            deliveryType = deliveryType,
            addressId = orderParam.deliveryParam.addressId,
        )

        val paymentMethodType = Payment.PaymentMethodType.fromString(orderParam.paymentParam.paymentMethodType)
            .getOrElse { errors ->
                logger.warn("$errors")
                throw OrderReceptionIllegalArgumentException(validationErrors = errors)
            }

        val payment =
            Payment.new(
                paymentMethodType = paymentMethodType,
                deliveryCharge = orderParam.paymentParam.deliveryCharge,
                nonTaxedTotalPrice = orderParam.paymentParam.nonTaxedTotalPrice,
                tax = orderParam.paymentParam.tax,
                taxedTotalPrice = orderParam.paymentParam.taxedTotalPrice
            )

        val order = Order.new(
            orderItem = orderItems,
            chainId = orderParam.chainId,
            shopId = orderParam.shopId,
            delivery = delivery,
            userId = orderParam.userId,
            payment = payment,
            time = orderParam.time
        )

        val registerOrderResponse = orderProcessingGrpcClient.registerOrder(order, user)

        return registerOrderResponse.orderId
    }
}