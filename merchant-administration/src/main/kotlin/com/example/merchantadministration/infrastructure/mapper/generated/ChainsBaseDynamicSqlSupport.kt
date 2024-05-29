/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.merchantadministration.infrastructure.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object ChainsBaseDynamicSqlSupport {
    val chainsBase = ChainsBase()

    val chainId = chainsBase.chainId

    val name = chainsBase.name

    val createdAt = chainsBase.createdAt

    val updatedAt = chainsBase.updatedAt

    class ChainsBase : AliasableSqlTable<ChainsBase>("chains", ::ChainsBase) {
        val chainId = column<Long>(name = "chain_id", jdbcType = JDBCType.BIGINT)

        val name = column<String>(name = "name", jdbcType = JDBCType.VARCHAR)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}