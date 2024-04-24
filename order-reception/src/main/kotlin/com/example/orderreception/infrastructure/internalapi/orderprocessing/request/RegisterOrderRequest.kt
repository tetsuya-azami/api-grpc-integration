package com.example.orderreception.infrastructure.internalapi.orderprocessing.request

import com.example.grpcinterface.proto.OrderOuterClass
import com.example.grpcinterface.proto.OrderOuterClass.*
import com.example.grpcinterface.proto.OrderOuterClass.Delivery.Type
import com.example.grpcinterface.proto.OrderOuterClass.Items.Attribute
import com.example.grpcinterface.proto.OrderOuterClass.Payment.PaymentMethod
import com.example.grpcinterface.proto.OrderOuterClass.User.BlackLevel
import com.example.orderreception.domain.model.order.OrderItem
import com.example.orderreception.domain.model.order.User
import com.example.orderreception.presentation.order.OrderParam
import com.google.protobuf.Timestamp
import java.time.ZoneOffset

data class RegisterOrderRequest private constructor(
    val orderCreationRequest: OrderCreationRequest
) {
    companion object {
        fun fromModel(orderParam: OrderParam, items: List<OrderItem>, user: User): RegisterOrderRequest {
            val orderItemsProto = items.map { item ->
                val attributesProto = item.attributes.map { attribute ->
                    Attribute.newBuilder()
                        .setId(attribute.id)
                        .setName(attribute.name)
                        .setValue(attribute.value)
                        .build()
                }

                Items.newBuilder()
                    .setId(item.itemId)
                    .setName(item.name)
                    .setPrice(item.price.toDouble())
                    .addAllAttributes(attributesProto)
                    .setQuantity(item.quantity)
                    .build()
            }

            return RegisterOrderRequest(
                OrderCreationRequest.newBuilder()
                    .setOrder(
                        Order.newBuilder()
                            .addAllItems(orderItemsProto)
                            .setChain(
                                Chain.newBuilder()
                                    .setId(orderParam.chainId)
                                    .build()
                            )
                            .setShop(
                                Shop.newBuilder()
                                    .setId(orderParam.shopId)
                                    .build()
                            )
                            .setDelivery(
                                Delivery.newBuilder()
                                    .setType(Type.valueOf(orderParam.deliveryParam.deliveryType.name))
                                    .setAddressId(orderParam.deliveryParam.addressId)
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
                                        PaymentMethod.valueOf(orderParam.paymentParam.paymentMethod.name)
                                    )
                                    .setDeliveryCharge(orderParam.paymentParam.deliveryCharge.toLong())
                                    .setNonTaxedTotalPrice(orderParam.paymentParam.nonTaxedTotalPrice.toLong())
                                    .setTax(orderParam.paymentParam.tax.toLong())
                                    .setTaxedTotalPrice(orderParam.paymentParam.taxedTotalPrice.toLong())
                                    .build()
                            )
                            .setTime(
                                Timestamp.newBuilder()
                                    .setSeconds(orderParam.time.toEpochSecond(ZoneOffset.of("+09:00")))
                                    .setNanos(0)
                                    .build()
                            )
                    ).build()
            )
        }
    }
}