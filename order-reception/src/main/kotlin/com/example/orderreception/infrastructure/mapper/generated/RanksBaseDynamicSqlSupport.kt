/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderreception.infrastructure.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object RanksBaseDynamicSqlSupport {
    val ranksBase = RanksBase()

    val rankId = ranksBase.rankId

    val name = ranksBase.name

    val createdAt = ranksBase.createdAt

    val updatedAt = ranksBase.updatedAt

    class RanksBase : AliasableSqlTable<RanksBase>("ranks", ::RanksBase) {
        val rankId = column<Long>(name = "rank_id", jdbcType = JDBCType.BIGINT)

        val name = column<String>(name = "name", jdbcType = JDBCType.VARCHAR)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}