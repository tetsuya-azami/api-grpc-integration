/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderreception.infrastructure.mapper.generated

import java.sql.JDBCType
import java.time.LocalDate
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object UsersBaseDynamicSqlSupport {
    val usersBase = UsersBase()

    val userId = usersBase.userId

    val firstName = usersBase.firstName

    val lastName = usersBase.lastName

    val phoneNumber = usersBase.phoneNumber

    val email = usersBase.email

    val password = usersBase.password

    val birthday = usersBase.birthday

    val rankId = usersBase.rankId

    val createdAt = usersBase.createdAt

    val updatedAt = usersBase.updatedAt

    class UsersBase : AliasableSqlTable<UsersBase>("users", ::UsersBase) {
        val userId = column<Long>(name = "user_id", jdbcType = JDBCType.BIGINT)

        val firstName = column<String>(name = "first_name", jdbcType = JDBCType.VARCHAR)

        val lastName = column<String>(name = "last_name", jdbcType = JDBCType.VARCHAR)

        val phoneNumber = column<String>(name = "phone_number", jdbcType = JDBCType.VARCHAR)

        val email = column<String>(name = "email", jdbcType = JDBCType.VARCHAR)

        val password = column<String>(name = "password", jdbcType = JDBCType.VARCHAR)

        val birthday = column<LocalDate>(name = "birthday", jdbcType = JDBCType.DATE)

        val rankId = column<Long>(name = "rank_id", jdbcType = JDBCType.BIGINT)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}