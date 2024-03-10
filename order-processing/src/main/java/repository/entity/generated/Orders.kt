/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-03-11T07:57:38.056354+09:00
 */
package repository.entity.generated

import java.util.Date

data class Orders(
    var orderId: Int? = null,
    var chainId: Int? = null,
    var shopId: Int? = null,
    var userId: Int? = null,
    var paymentMethod: String? = null,
    var deliveryAddressId: Int? = null,
    var deliveryType: String? = null,
    var deliveryMethodId: Int? = null,
    var deliveryCharge: Long? = null,
    var nonTaxedTotalPrice: Long? = null,
    var tax: Long? = null,
    var taxedTotalPrice: Long? = null,
    var time: Date? = null,
    var createdAt: Date? = null,
    var updatedAt: Date? = null
)