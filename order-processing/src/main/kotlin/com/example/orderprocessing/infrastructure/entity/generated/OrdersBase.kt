/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.infrastructure.entity.generated

import java.time.LocalDateTime

data class OrdersBase(
    var orderId: String? = null,
    var chainId: Long? = null,
    var shopId: Long? = null,
    var userId: Long? = null,
    var paymentMethod: String? = null,
    var deliveryAddressId: Long? = null,
    var deliveryType: String? = null,
    var deliveryCharge: Long? = null,
    var nonTaxedTotalPrice: Long? = null,
    var tax: Long? = null,
    var taxedTotalPrice: Long? = null,
    var time: LocalDateTime? = null,
    var blackLevel: Int? = null,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
)