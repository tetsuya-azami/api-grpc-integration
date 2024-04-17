package com.example.orderreception.usecase

import com.example.orderreception.error.ValidationError
import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException
import com.example.orderreception.presentation.order.OrderParam
import com.example.orderreception.usecase.query.AddressQuery
import com.example.orderreception.usecase.query.UserQuery
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RegisterOrder(
    val userQuery: UserQuery,
    val addressQuery: AddressQuery
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    fun execute(orderParam: OrderParam) {
        val user =
            userQuery.findById(orderParam.userId)
                ?: run {
                    logger.warn("存在しないユーザです。userId: ${orderParam.userId}")
                    throw OrderReceptionIllegalArgumentException(
                        validationErrors = listOf(ValidationError("存在しないユーザです。userId: ${orderParam.userId}"))
                    )
                }

        if (user.isBlackUser()) {
            logger.warn("ブラックリストユーザです。userId: ${orderParam.userId}")
            throw OrderReceptionIllegalArgumentException(
                validationErrors = listOf(ValidationError("ブラックリストユーザです。userId: ${orderParam.userId}"))
            )
        }

        val address = addressQuery.findById(orderParam.deliveryParam.addressId, orderParam.userId)
            ?: kotlin.run {
                logger.warn("存在しない配達先住所です。userId: ${orderParam.userId}, addressId: ${orderParam.deliveryParam.addressId}")
                throw OrderReceptionIllegalArgumentException(
                    validationErrors = listOf(ValidationError("存在しない配達先住所です。userId: ${orderParam.userId}, addressId: ${orderParam.deliveryParam.addressId}"))
                )
            }

        // ClientServiceをつくってorder-processingに渡す
    }
}