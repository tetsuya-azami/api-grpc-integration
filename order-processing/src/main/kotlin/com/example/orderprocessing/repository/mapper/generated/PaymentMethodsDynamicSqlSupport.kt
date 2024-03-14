/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.repository.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object PaymentMethodsDynamicSqlSupport {
    val paymentMethods = PaymentMethods()

    val name = paymentMethods.name

    val createdAt = paymentMethods.createdAt

    val updatedAt = paymentMethods.updatedAt

    class PaymentMethods : AliasableSqlTable<PaymentMethods>("payment_methods", ::PaymentMethods) {
        val name = column<String>(name = "name", jdbcType = JDBCType.VARCHAR)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}