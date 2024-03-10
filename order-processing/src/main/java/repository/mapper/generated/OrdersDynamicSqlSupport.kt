/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-03-11T07:57:38.057449+09:00
 */
package repository.mapper.generated

import java.sql.JDBCType
import java.util.Date
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

    val deliveryMethodId = orders.deliveryMethodId

    val deliveryCharge = orders.deliveryCharge

    val nonTaxedTotalPrice = orders.nonTaxedTotalPrice

    val tax = orders.tax

    val taxedTotalPrice = orders.taxedTotalPrice

    val time = orders.time

    val createdAt = orders.createdAt

    val updatedAt = orders.updatedAt

    class Orders : AliasableSqlTable<Orders>("orders", ::Orders) {
        val orderId = column<Int>(name = "order_id", jdbcType = JDBCType.INTEGER)

        val chainId = column<Int>(name = "chain_id", jdbcType = JDBCType.INTEGER)

        val shopId = column<Int>(name = "shop_id", jdbcType = JDBCType.INTEGER)

        val userId = column<Int>(name = "user_id", jdbcType = JDBCType.INTEGER)

        val paymentMethod = column<String>(name = "payment_method", jdbcType = JDBCType.VARCHAR)

        val deliveryAddressId = column<Int>(name = "delivery_address_id", jdbcType = JDBCType.INTEGER)

        val deliveryType = column<String>(name = "delivery_type", jdbcType = JDBCType.VARCHAR)

        val deliveryMethodId = column<Int>(name = "delivery_method_id", jdbcType = JDBCType.INTEGER)

        val deliveryCharge = column<Long>(name = "delivery_charge", jdbcType = JDBCType.DECIMAL)

        val nonTaxedTotalPrice = column<Long>(name = "non_taxed_total_price", jdbcType = JDBCType.DECIMAL)

        val tax = column<Long>(name = "tax", jdbcType = JDBCType.DECIMAL)

        val taxedTotalPrice = column<Long>(name = "taxed_total_price", jdbcType = JDBCType.DECIMAL)

        val time = column<Date>(name = "time", jdbcType = JDBCType.TIMESTAMP)

        val createdAt = column<Date>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<Date>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}