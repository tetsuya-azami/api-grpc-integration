/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.repository.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object AttributesDynamicSqlSupport {
    val attributes = Attributes()

    val attributeId = attributes.attributeId

    val name = attributes.name

    val value = attributes.value

    val createdAt = attributes.createdAt

    val updatedAt = attributes.updatedAt

    class Attributes : AliasableSqlTable<Attributes>("attributes", ::Attributes) {
        val attributeId = column<Long>(name = "attribute_id", jdbcType = JDBCType.BIGINT)

        val name = column<String>(name = "name", jdbcType = JDBCType.VARCHAR)

        val value = column<String>(name = "value", jdbcType = JDBCType.VARCHAR)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}