/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-03-11T09:10:07.204853+09:00
 */
package repository.mapper.generated

import java.sql.JDBCType
import java.util.Date
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
        val attributeId = column<Int>(name = "attribute_id", jdbcType = JDBCType.INTEGER)

        val name = column<String>(name = "name", jdbcType = JDBCType.VARCHAR)

        val value = column<String>(name = "value", jdbcType = JDBCType.VARCHAR)

        val createdAt = column<Date>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<Date>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}