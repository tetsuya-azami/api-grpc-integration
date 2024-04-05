/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.infrastructure.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object ItemAttributesBaseDynamicSqlSupport {
    val itemAttributesBase = ItemAttributesBase()

    val itemId = itemAttributesBase.itemId

    val attributeId = itemAttributesBase.attributeId

    val createdAt = itemAttributesBase.createdAt

    val updatedAt = itemAttributesBase.updatedAt

    class ItemAttributesBase : AliasableSqlTable<ItemAttributesBase>("item_attributes", ::ItemAttributesBase) {
        val itemId = column<Long>(name = "item_id", jdbcType = JDBCType.BIGINT)

        val attributeId = column<Long>(name = "attribute_id", jdbcType = JDBCType.BIGINT)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}