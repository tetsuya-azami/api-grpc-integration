package com.example.orderreception.usecase

import com.example.orderreception.error.ValidationError
import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException
import com.example.orderreception.infrastructure.internalapi.orderprocessing.client.OrderProcessingGrpcClient
import com.example.orderreception.presentation.order.OrderParam
import com.example.orderreception.usecase.query.AddressQuery
import com.example.orderreception.usecase.query.OrderItemFactory
import com.example.orderreception.usecase.query.UserQuery
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

        val items = orderItemFactory.createOrderItems(
            orderItemParams = orderParam.orderItemParams,
            chainId = orderParam.chainId,
            shopId = orderParam.shopId
        )
        // TODO: パラメータで渡されたpriceとDBのpriceの相関チェック

        // TODO: Orderモデルを作って渡すようにする
        val registerOrderResponse = orderProcessingGrpcClient.registerOrder(orderParam, items, user)

        return registerOrderResponse.orderId
    }
}