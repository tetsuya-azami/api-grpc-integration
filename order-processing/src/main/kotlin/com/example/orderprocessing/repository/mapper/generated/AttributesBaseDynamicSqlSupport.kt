/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.repository.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object AttributesBaseDynamicSqlSupport {
    val attributesBase = AttributesBase()

    val attributeId = attributesBase.attributeId

    val name = attributesBase.name

    val value = attributesBase.value

    val createdAt = attributesBase.createdAt

    val updatedAt = attributesBase.updatedAt

    class AttributesBase : AliasableSqlTable<AttributesBase>("attributes", ::AttributesBase) {
        val attributeId = column<Long>(name = "attribute_id", jdbcType = JDBCType.BIGINT)

        val name = column<String>(name = "name", jdbcType = JDBCType.VARCHAR)

        val value = column<String>(name = "value", jdbcType = JDBCType.VARCHAR)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}