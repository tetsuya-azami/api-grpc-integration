/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.repository.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object OrdersDynamicSqlSupport {
    val orders = Orders()

    val orderId = orders.orderId

    val chainId = orders.chainId

    val shopId = orders.shopId

    val userId = orders.userId

    val paymentMethod = orders.paymentMethod

    val deliveryAddressId = orders.deliveryAddressId

    val deliveryType = orders.deliveryType

    val deliveryCharge = orders.deliveryCharge

    val nonTaxedTotalPrice = orders.nonTaxedTotalPrice

    val tax = orders.tax

    val taxedTotalPrice = orders.taxedTotalPrice

    val time = orders.time

    val createdAt = orders.createdAt

    val updatedAt = orders.updatedAt

    class Orders : AliasableSqlTable<Orders>("orders", ::Orders) {
        val orderId = column<Long>(name = "order_id", jdbcType = JDBCType.BIGINT)

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