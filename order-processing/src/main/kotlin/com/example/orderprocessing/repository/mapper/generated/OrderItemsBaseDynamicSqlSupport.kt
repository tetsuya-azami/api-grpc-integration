/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.repository.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object OrderItemsBaseDynamicSqlSupport {
    val orderItemsBase = OrderItemsBase()

    val orderId = orderItemsBase.orderId

    val itemId = orderItemsBase.itemId

    val quantity = orderItemsBase.quantity

    val createdAt = orderItemsBase.createdAt

    val updatedAt = orderItemsBase.updatedAt

    class OrderItemsBase : AliasableSqlTable<OrderItemsBase>("order_items", ::OrderItemsBase) {
        val orderId = column<String>(name = "order_id", jdbcType = JDBCType.VARCHAR)

        val itemId = column<Long>(name = "item_id", jdbcType = JDBCType.BIGINT)

        val quantity = column<Int>(name = "quantity", jdbcType = JDBCType.INTEGER)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}