/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.repository.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object ItemAttributesDynamicSqlSupport {
    val itemAttributes = ItemAttributes()

    val itemId = itemAttributes.itemId

    val attributeId = itemAttributes.attributeId

    val createdAt = itemAttributes.createdAt

    val updatedAt = itemAttributes.updatedAt

    class ItemAttributes : AliasableSqlTable<ItemAttributes>("item_attributes", ::ItemAttributes) {
        val itemId = column<Long>(name = "item_id", jdbcType = JDBCType.BIGINT)

        val attributeId = column<Long>(name = "attribute_id", jdbcType = JDBCType.BIGINT)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}