/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-03-11T09:23:44.887725+09:00
 */
package com.example.orderprocessing.repository.mapper.generated

import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object ItemAttributesDynamicSqlSupport {
    val itemAttributes = ItemAttributes()

    val itemId = itemAttributes.itemId

    val attributeId = itemAttributes.attributeId

    val createdAt = itemAttributes.createdAt

    val updatedAt = itemAttributes.updatedAt

    class ItemAttributes : AliasableSqlTable<ItemAttributes>("item_attributes", ::ItemAttributes) {
        val itemId = column<Int>(name = "item_id", jdbcType = JDBCType.INTEGER)

        val attributeId = column<Int>(name = "attribute_id", jdbcType = JDBCType.INTEGER)

        val createdAt = column<Date>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<Date>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}