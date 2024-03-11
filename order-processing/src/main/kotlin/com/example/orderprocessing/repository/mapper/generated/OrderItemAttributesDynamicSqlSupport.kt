/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-03-11T09:23:44.887983+09:00
 */
package com.example.orderprocessing.repository.mapper.generated

import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object OrderItemAttributesDynamicSqlSupport {
    val orderItemAttributes = OrderItemAttributes()

    val orderId = orderItemAttributes.orderId

    val itemId = orderItemAttributes.itemId

    val attributeId = orderItemAttributes.attributeId

    val createdAt = orderItemAttributes.createdAt

    val updatedAt = orderItemAttributes.updatedAt

    class OrderItemAttributes : AliasableSqlTable<OrderItemAttributes>("order_item_attributes", ::OrderItemAttributes) {
        val orderId = column<Int>(name = "order_id", jdbcType = JDBCType.INTEGER)

        val itemId = column<Int>(name = "item_id", jdbcType = JDBCType.INTEGER)

        val attributeId = column<Int>(name = "attribute_id", jdbcType = JDBCType.INTEGER)

        val createdAt = column<Date>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<Date>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}