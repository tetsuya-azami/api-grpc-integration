/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.repository.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object ItemsDynamicSqlSupport {
    val items = Items()

    val itemId = items.itemId

    val chainId = items.chainId

    val shopId = items.shopId

    val name = items.name

    val price = items.price

    val description = items.description

    val createdAt = items.createdAt

    val updatedAt = items.updatedAt

    class Items : AliasableSqlTable<Items>("items", ::Items) {
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