/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.repository.entity.generated

import java.time.LocalDateTime

data class Orders(
    var orderId: Long? = null,
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
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
)