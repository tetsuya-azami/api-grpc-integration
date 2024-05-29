/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.merchantadministration.infrastructure.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object ItemsBaseDynamicSqlSupport {
    val itemsBase = ItemsBase()

    val itemId = itemsBase.itemId

    val chainId = itemsBase.chainId

    val shopId = itemsBase.shopId

    val name = itemsBase.name

    val price = itemsBase.price

    val description = itemsBase.description

    val createdAt = itemsBase.createdAt

    val updatedAt = itemsBase.updatedAt

    class ItemsBase : AliasableSqlTable<ItemsBase>("items", ::ItemsBase) {
        val itemId = column<Long>(name = "item_id", jdbcType = JDBCType.BIGINT)

        val chainId = column<Long>(name = "chain_id", jdbcType = JDBCType.BIGINT)

        val shopId = column<Long>(name = "shop_id", jdbcType = JDBCType.BIGINT)

        val name = column<String>(name = "name", jdbcType = JDBCType.VARCHAR)

        val price = column<Long>(name = "price", jdbcType = JDBCType.DECIMAL)

        val description = column<String>(name = "description", jdbcType = JDBCType.VARCHAR)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}