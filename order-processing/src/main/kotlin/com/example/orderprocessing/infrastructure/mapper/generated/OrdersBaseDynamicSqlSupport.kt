/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.infrastructure.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object OrdersBaseDynamicSqlSupport {
    val ordersBase = OrdersBase()

    val orderId = ordersBase.orderId

    val chainId = ordersBase.chainId

    val shopId = ordersBase.shopId

    val userId = ordersBase.userId

    val paymentMethod = ordersBase.paymentMethod

    val deliveryAddressId = ordersBase.deliveryAddressId

    val deliveryType = ordersBase.deliveryType

    val deliveryCharge = ordersBase.deliveryCharge

    val nonTaxedTotalPrice = ordersBase.nonTaxedTotalPrice

    val tax = ordersBase.tax

    val taxedTotalPrice = ordersBase.taxedTotalPrice

    val time = ordersBase.time

    val createdAt = ordersBase.createdAt

    val updatedAt = ordersBase.updatedAt

    class OrdersBase : AliasableSqlTable<OrdersBase>("orders", ::OrdersBase) {
        val orderId = column<String>(name = "order_id", jdbcType = JDBCType.VARCHAR)

        val chainId = column<Long>(name = "chain_id", jdbcType = JDBCType.BIGINT)

        val shopId = column<Long>(name = "shop_id", jdbcType = JDBCType.BIGINT)

        val userId = column<Long>(name = "user_id", jdbcType = JDBCType.BIGINT)

        val paymentMethod = column<String>(name = "payment_method", jdbcType = JDBCType.VARCHAR)

        val deliveryAddressId = column<Long>(name = "delivery_address_id", jdbcType = JDBCType.BIGINT)

        val deliveryType = column<String>(name = "delivery_type", jdbcType = JDBCType.VARCHAR)

        val deliveryCharge = column<Long>(name = "delivery_charge", jdbcType = JDBCType.DECIMAL)

        val nonTaxedTotalPrice = column<Long>(name = "non_taxed_total_price", jdbcType = JDBCType.DECIMAL)

        val tax = column<Long>(name = "tax", jdbcType = JDBCType.DECIMAL)

        val taxedTotalPrice = column<Long>(name = "taxed_total_price", jdbcType = JDBCType.DECIMAL)

        val time = column<LocalDateTime>(name = "time", jdbcType = JDBCType.TIMESTAMP)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}