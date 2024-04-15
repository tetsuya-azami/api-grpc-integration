/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderreception.infrastructure.mapper.generated

import java.sql.JDBCType
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object UsersBaseDynamicSqlSupport {
    val usersBase = UsersBase()

    val user = usersBase.user

    val currentConnections = usersBase.currentConnections

    val totalConnections = usersBase.totalConnections

    val maxSessionControlledMemory = usersBase.maxSessionControlledMemory

    val maxSessionTotalMemory = usersBase.maxSessionTotalMemory

    class UsersBase : AliasableSqlTable<UsersBase>("users", ::UsersBase) {
        val user = column<String>(name = "USER", jdbcType = JDBCType.CHAR)

        val currentConnections = column<Long>(name = "CURRENT_CONNECTIONS", jdbcType = JDBCType.BIGINT)

        val totalConnections = column<Long>(name = "TOTAL_CONNECTIONS", jdbcType = JDBCType.BIGINT)

        val maxSessionControlledMemory = column<Long>(name = "MAX_SESSION_CONTROLLED_MEMORY", jdbcType = JDBCType.BIGINT)

        val maxSessionTotalMemory = column<Long>(name = "MAX_SESSION_TOTAL_MEMORY", jdbcType = JDBCType.BIGINT)
    }
}