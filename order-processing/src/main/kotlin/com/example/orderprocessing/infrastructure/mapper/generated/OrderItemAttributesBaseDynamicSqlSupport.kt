/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.infrastructure.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object OrderItemAttributesBaseDynamicSqlSupport {
    val orderItemAttributesBase = OrderItemAttributesBase()

    val orderId = orderItemAttributesBase.orderId

    val itemId = orderItemAttributesBase.itemId

    val attributeId = orderItemAttributesBase.attributeId

    val createdAt = orderItemAttributesBase.createdAt

    val updatedAt = orderItemAttributesBase.updatedAt

    class OrderItemAttributesBase : AliasableSqlTable<OrderItemAttributesBase>("order_item_attributes", ::OrderItemAttributesBase) {
        val orderId = column<String>(name = "order_id", jdbcType = JDBCType.VARCHAR)

        val itemId = column<Long>(name = "item_id", jdbcType = JDBCType.BIGINT)

        val attributeId = column<Long>(name = "attribute_id", jdbcType = JDBCType.BIGINT)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}