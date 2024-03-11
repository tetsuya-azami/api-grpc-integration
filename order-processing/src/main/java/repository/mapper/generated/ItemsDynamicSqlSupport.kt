/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-03-11T09:10:07.202408+09:00
 */
package repository.mapper.generated

import java.sql.JDBCType
import java.util.Date
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
        val itemId = column<Int>(name = "item_id", jdbcType = JDBCType.INTEGER)

        val chainId = column<Int>(name = "chain_id", jdbcType = JDBCType.INTEGER)

        val shopId = column<Int>(name = "shop_id", jdbcType = JDBCType.INTEGER)

        val name = column<String>(name = "name", jdbcType = JDBCType.VARCHAR)

        val price = column<Long>(name = "price", jdbcType = JDBCType.DECIMAL)

        val description = column<String>(name = "description", jdbcType = JDBCType.VARCHAR)

        val createdAt = column<Date>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<Date>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}