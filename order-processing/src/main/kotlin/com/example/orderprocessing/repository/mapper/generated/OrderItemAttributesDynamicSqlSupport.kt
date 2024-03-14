/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.repository.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
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
        val orderId = column<Long>(name = "order_id", jdbcType = JDBCType.BIGINT)

        val itemId = column<Long>(name = "item_id", jdbcType = JDBCType.BIGINT)

        val attributeId = column<Long>(name = "attribute_id", jdbcType = JDBCType.BIGINT)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}