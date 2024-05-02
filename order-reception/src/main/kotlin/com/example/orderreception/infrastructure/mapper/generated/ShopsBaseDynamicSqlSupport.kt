/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderreception.infrastructure.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object ShopsBaseDynamicSqlSupport {
    val shopsBase = ShopsBase()

    val shopId = shopsBase.shopId

    val chainId = shopsBase.chainId

    val name = shopsBase.name

    val createdAt = shopsBase.createdAt

    val updatedAt = shopsBase.updatedAt

    class ShopsBase : AliasableSqlTable<ShopsBase>("shops", ::ShopsBase) {
        val shopId = column<Long>(name = "shop_id", jdbcType = JDBCType.BIGINT)

        val chainId = column<Long>(name = "chain_id", jdbcType = JDBCType.BIGINT)

        val name = column<String>(name = "name", jdbcType = JDBCType.VARCHAR)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}