/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-03-11T09:23:44.888755+09:00
 */
package com.example.orderprocessing.repository.mapper.generated

import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object PaymentMethodsDynamicSqlSupport {
    val paymentMethods = PaymentMethods()

    val name = paymentMethods.name

    val createdAt = paymentMethods.createdAt

    val updatedAt = paymentMethods.updatedAt

    class PaymentMethods : AliasableSqlTable<PaymentMethods>("payment_methods", ::PaymentMethods) {
        val name = column<String>(name = "name", jdbcType = JDBCType.VARCHAR)

        val createdAt = column<Date>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<Date>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}