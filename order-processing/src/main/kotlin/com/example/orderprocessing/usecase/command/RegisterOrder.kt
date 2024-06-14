package com.example.orderprocessing.usecase.command

import com.example.orderprocessing.domain.model.*
import com.example.orderprocessing.error.exception.OrderProcessingIllegalArgumentException
import com.example.orderprocessing.infrastructure.repository.order.OrderRepository
import com.example.orderprocessing.presentation.order.OrderParam
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.get
import com.github.michaelbull.result.mapEither
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class RegisterOrder(
    private val orderRepository: OrderRepository
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Transactional
    fun execute(orderParam: OrderParam): OrderId {
        logger.info("注文登録処理開始")
        val now = LocalDateTime.now()

        // TODO: 本来はzipOrAccumulateを使いたい。kotlin-resultにはない。。。自作するか？
        val orderItemsValue = orderParam.orderItemParams.map { orderItemParam ->
            OrderItemAttributes.new(
                itemId = orderItemParam.id,
                value = orderItemParam.attributeParams.map {
                    OrderItemAttribute.new(
                        itemId = orderItemParam.id,
                        attributeId = it.id,
                        name = it.name,
                        value = it.value
                    )
                }
            ).andThen { orderItemAttributes ->
                OrderItem.new(
                    itemId = orderItemParam.id,
                    price = orderItemParam.price,
                    quantity = orderItemParam.quantity,
                    orderItemAttributes = orderItemAttributes,
                )
            }.mapEither(
                success = { it },
                failure = { throw OrderProcessingIllegalArgumentException(it) }
            ).get()!!
        }

        val orderItems = OrderItems.new(orderItemsValue)
            .mapEither(
                success = { it },
                failure = { throw OrderProcessingIllegalArgumentException(it) }
            ).get()!!

        val delivery = orderParam.deliveryParam.let { deliveryParam ->
            DeliveryType
                .fromString(deliveryParam.deliveryType)
                .andThen { deliveryType ->
                    Ok(Delivery.new(type = deliveryType, addressId = orderParam.deliveryParam.addressId))
                }.mapEither(
                    success = { it },
                    failure = { throw OrderProcessingIllegalArgumentException(it) }
                ).get()!!
        }

        val user = User.new(orderParam.userParam.id)

        val payment = orderParam.paymentParam.let { paymentParam ->
            PaymentMethodType
                .fromString(paymentParam.paymentMethod)
                .andThen { paymentMethodType ->
                    Payment.new(
                        paymentMethodType = paymentMethodType,
                        deliveryCharge = paymentParam.deliveryCharge,
                        nonTaxedTotalPrice = paymentParam.nonTaxedTotalPrice,
                        tax = paymentParam.tax,
                        taxedTotalPrice = paymentParam.taxedTotalPrice
                    )
                }.mapEither(
                    success = { it },
                    failure = { throw OrderProcessingIllegalArgumentException(it) }
                ).get()!!
        }

        val blackLevel = orderParam.blackLevel.let { blackLevel ->
            BlackLevel
                .fromString(blackLevel)
                .mapEither(
                    success = { it },
                    failure = { throw OrderProcessingIllegalArgumentException(it) }
                ).get()!!
        }

        val order = Order.new(
            orderItems = orderItems,
            chainId = orderParam.chainId,
            shopId = orderParam.shopId,
            delivery = delivery,
            user = user,
            payment = payment,
            blackLevel = blackLevel,
            time = orderParam.time
        ).mapEither(
            success = { it },
            failure = { throw OrderProcessingIllegalArgumentException(it) }
        ).get()!!

        return orderRepository
            .registerOrder(order, now)
            .let {
                logger.info("注文登録処理終了")
                it
            }
    }
}