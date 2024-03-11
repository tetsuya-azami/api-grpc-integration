/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-03-11T09:10:07.203547+09:00
 */
package repository.mapper.generated

import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object OrderItemsDynamicSqlSupport {
    val orderItems = OrderItems()

    val orderId = orderItems.orderId

    val itemId = orderItems.itemId

    val quantity = orderItems.quantity

    val createdAt = orderItems.createdAt

    val updatedAt = orderItems.updatedAt

    class OrderItems : AliasableSqlTable<OrderItems>("order_items", ::OrderItems) {
        val orderId = column<Int>(name = "order_id", jdbcType = JDBCType.INTEGER)

        val itemId = column<Int>(name = "item_id", jdbcType = JDBCType.INTEGER)

        val quantity = column<Int>(name = "quantity", jdbcType = JDBCType.INTEGER)

        val createdAt = column<Date>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<Date>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}