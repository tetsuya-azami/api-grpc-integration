package com.example.orderreception.infrastructure.api.internal.orderprocessing.request

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.grpcinterface.proto.OrderOuterClass.*
import com.example.grpcinterface.proto.OrderOuterClass.Delivery.Type
import com.example.grpcinterface.proto.OrderOuterClass.Payment.PaymentMethod
import com.example.grpcinterface.proto.OrderOuterClass.User.BlackLevel
import com.example.orderreception.domain.model.order.User
import com.google.protobuf.Timestamp
import com.google.type.Money
import java.time.ZoneOffset

data class RegisterOrderRequest private constructor(
    val orderCreationRequest: OrderCreationRequest
) {
    companion object {
        fun fromModel(order: com.example.orderreception.domain.model.order.Order, user: User): RegisterOrderRequest {
            val orderItems = order.orderItems
            val orderItemsProto = orderItems.map { orderItem ->
                val attributesProto = orderItem.attributes.map { attribute ->
                    OrderItem.Attribute.newBuilder()
                        .setId(attribute.id)
                        .setName(attribute.name)
                        .setValue(attribute.value)
                        .build()
                }

                OrderItem.newBuilder()
                    .setId(orderItem.itemId)
                    .setName(orderItem.name)
                    .setPrice(
                        Money.newBuilder()
                            .setUnits(orderItem.price.toLong())
                            .setNanos(0)
                            .setCurrencyCode("JPY")
                    )
                    .addAllAttributes(attributesProto)
                    .setQuantity(orderItem.quantity)
                    .build()
            }

            return RegisterOrderRequest(
                OrderCreationRequest.newBuilder()
                    .setOrder(
                        Order.newBuilder()
                            .addAllOrderItems(orderItemsProto)
                            .setChain(
                                Chain.newBuilder()
                                    .setId(order.chainId)
                                    .build()
                            )
                            .setShop(
                                Shop.newBuilder()
                                    .setId(order.shopId)
                                    .build()
                            )
                            .setDelivery(
                                Delivery.newBuilder()
                                    .setType(Type.valueOf(order.delivery.deliveryType.name))
                                    .setAddressId(order.delivery.addressId)
                                    .build()
                            )
                            .setUser(
                                OrderOuterClass.User.newBuilder()
                                    .setId(user.id)
                                    .setBlackLevel(
                                        BlackLevel.valueOf(user.blackLevel.name)
                                    )
                                    .build()
                            )
                            .setPayment(
                                Payment.newBuilder()
                                    .setPaymentMethod(
                                        PaymentMethod.valueOf(order.payment.paymentMethodType.name)
                                    )
                                    .setDeliveryCharge(
                                        Money.newBuilder()
                                            .setUnits(order.payment.deliveryCharge.toLong())
                                            .setNanos(0)
                                            .setCurrencyCode("JPY")
                                    )
                                    .setNonTaxedTotalPrice(
                                        Money.newBuilder()
                                            .setUnits(order.payment.nonTaxedTotalPrice.toLong())
                                            .setNanos(0)
                                            .setCurrencyCode("JPY")
                                    )
                                    .setTax(
                                        Money.newBuilder()
                                            .setUnits(order.payment.tax.toLong())
                                            .setNanos(0)
                                            .setCurrencyCode("JPY")
                                    )
                                    .setTaxedTotalPrice(
                                        Money.newBuilder()
                                            .setUnits(order.payment.taxedTotalPrice.toLong())
                                            .setNanos(0)
                                            .setCurrencyCode("JPY")
                                    )
                                    .build()
                            )
                            .setTime(
                                Timestamp.newBuilder()
                                    .setSeconds(order.time.toEpochSecond(ZoneOffset.of("+09:00")))
                                    .setNanos(0)
                                    .build()
                            )
                    ).build()
            )
        }
    }
}