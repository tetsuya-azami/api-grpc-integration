/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-03-14T08:47:24.857777+09:00
 */
package com.example.orderprocessing.repository.mapper.generated

import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object DeliveryMethodsDynamicSqlSupport {
    val deliveryMethods = DeliveryMethods()

    val name = deliveryMethods.name

    val createdAt = deliveryMethods.createdAt

    val updatedAt = deliveryMethods.updatedAt

    class DeliveryMethods : AliasableSqlTable<DeliveryMethods>("delivery_methods", ::DeliveryMethods) {
        val name = column<String>(name = "name", jdbcType = JDBCType.VARCHAR)

        val createdAt = column<Date>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<Date>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}