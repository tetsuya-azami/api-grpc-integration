/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.repository.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object OrderItemsDynamicSqlSupport {
    val orderItems = OrderItems()

    val orderId = orderItems.orderId

    val itemId = orderItems.itemId

    val quantity = orderItems.quantity

    val createdAt = orderItems.createdAt

    val updatedAt = orderItems.updatedAt

    class OrderItems : AliasableSqlTable<OrderItems>("order_items", ::OrderItems) {
        val orderId = column<Long>(name = "order_id", jdbcType = JDBCType.BIGINT)

        val itemId = column<Long>(name = "item_id", jdbcType = JDBCType.BIGINT)

        val quantity = column<Int>(name = "quantity", jdbcType = JDBCType.INTEGER)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}