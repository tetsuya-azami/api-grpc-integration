/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.infrastructure.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object PaymentMethodsBaseDynamicSqlSupport {
    val paymentMethodsBase = PaymentMethodsBase()

    val name = paymentMethodsBase.name

    val createdAt = paymentMethodsBase.createdAt

    val updatedAt = paymentMethodsBase.updatedAt

    class PaymentMethodsBase : AliasableSqlTable<PaymentMethodsBase>("payment_methods", ::PaymentMethodsBase) {
        val name = column<String>(name = "name", jdbcType = JDBCType.VARCHAR)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}