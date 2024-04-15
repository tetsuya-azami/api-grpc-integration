/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderreception.infrastructure.mapper.generated

import com.example.orderreception.infrastructure.entity.generated.UsersBase
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseDynamicSqlSupport.birthday
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseDynamicSqlSupport.blackLevel
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseDynamicSqlSupport.createdAt
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseDynamicSqlSupport.email
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseDynamicSqlSupport.firstName
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseDynamicSqlSupport.lastName
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseDynamicSqlSupport.password
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseDynamicSqlSupport.phoneNumber
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseDynamicSqlSupport.rankId
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseDynamicSqlSupport.updatedAt
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseDynamicSqlSupport.userId
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseDynamicSqlSupport.usersBase
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.util.kotlin.CountCompleter
import org.mybatis.dynamic.sql.util.kotlin.DeleteCompleter
import org.mybatis.dynamic.sql.util.kotlin.KotlinUpdateBuilder
import org.mybatis.dynamic.sql.util.kotlin.SelectCompleter
import org.mybatis.dynamic.sql.util.kotlin.UpdateCompleter
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.countFrom
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.deleteFrom
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insert
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insertMultiple
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.selectDistinct
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.selectList
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.selectOne
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper

@Mapper
interface UsersBaseMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<UsersBase>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="UsersBaseResult", value = [
        Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT, id=true),
        Result(column="first_name", property="firstName", jdbcType=JdbcType.VARCHAR),
        Result(column="last_name", property="lastName", jdbcType=JdbcType.VARCHAR),
        Result(column="phone_number", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        Result(column="rank_id", property="rankId", jdbcType=JdbcType.BIGINT),
        Result(column="black_level", property="blackLevel", jdbcType=JdbcType.INTEGER),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<UsersBase>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("UsersBaseResult")
    fun selectOne(selectStatement: SelectStatementProvider): UsersBase?
}

fun UsersBaseMapper.count(completer: CountCompleter) =
    countFrom(this::count, usersBase, completer)

fun UsersBaseMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, usersBase, completer)

fun UsersBaseMapper.deleteByPrimaryKey(userId_: Long) =
    delete {
        where { userId isEqualTo userId_ }
    }

fun UsersBaseMapper.insert(row: UsersBase) =
    insert(this::insert, row, usersBase) {
        map(userId) toProperty "userId"
        map(firstName) toProperty "firstName"
        map(lastName) toProperty "lastName"
        map(phoneNumber) toProperty "phoneNumber"
        map(email) toProperty "email"
        map(password) toProperty "password"
        map(birthday) toProperty "birthday"
        map(rankId) toProperty "rankId"
        map(blackLevel) toProperty "blackLevel"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun UsersBaseMapper.insertMultiple(records: Collection<UsersBase>) =
    insertMultiple(this::insertMultiple, records, usersBase) {
        map(userId) toProperty "userId"
        map(firstName) toProperty "firstName"
        map(lastName) toProperty "lastName"
        map(phoneNumber) toProperty "phoneNumber"
        map(email) toProperty "email"
        map(password) toProperty "password"
        map(birthday) toProperty "birthday"
        map(rankId) toProperty "rankId"
        map(blackLevel) toProperty "blackLevel"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun UsersBaseMapper.insertMultiple(vararg records: UsersBase) =
    insertMultiple(records.toList())

fun UsersBaseMapper.insertSelective(row: UsersBase) =
    insert(this::insert, row, usersBase) {
        map(userId).toPropertyWhenPresent("userId", row::userId)
        map(firstName).toPropertyWhenPresent("firstName", row::firstName)
        map(lastName).toPropertyWhenPresent("lastName", row::lastName)
        map(phoneNumber).toPropertyWhenPresent("phoneNumber", row::phoneNumber)
        map(email).toPropertyWhenPresent("email", row::email)
        map(password).toPropertyWhenPresent("password", row::password)
        map(birthday).toPropertyWhenPresent("birthday", row::birthday)
        map(rankId).toPropertyWhenPresent("rankId", row::rankId)
        map(blackLevel).toPropertyWhenPresent("blackLevel", row::blackLevel)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(userId, firstName, lastName, phoneNumber, email, password, birthday, rankId, blackLevel, createdAt, updatedAt)

fun UsersBaseMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, usersBase, completer)

fun UsersBaseMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, usersBase, completer)

fun UsersBaseMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, usersBase, completer)

fun UsersBaseMapper.selectByPrimaryKey(userId_: Long) =
    selectOne {
        where { userId isEqualTo userId_ }
    }

fun UsersBaseMapper.update(completer: UpdateCompleter) =
    update(this::update, usersBase, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: UsersBase) =
    apply {
        set(userId) equalToOrNull row::userId
        set(firstName) equalToOrNull row::firstName
        set(lastName) equalToOrNull row::lastName
        set(phoneNumber) equalToOrNull row::phoneNumber
        set(email) equalToOrNull row::email
        set(password) equalToOrNull row::password
        set(birthday) equalToOrNull row::birthday
        set(rankId) equalToOrNull row::rankId
        set(blackLevel) equalToOrNull row::blackLevel
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: UsersBase) =
    apply {
        set(userId) equalToWhenPresent row::userId
        set(firstName) equalToWhenPresent row::firstName
        set(lastName) equalToWhenPresent row::lastName
        set(phoneNumber) equalToWhenPresent row::phoneNumber
        set(email) equalToWhenPresent row::email
        set(password) equalToWhenPresent row::password
        set(birthday) equalToWhenPresent row::birthday
        set(rankId) equalToWhenPresent row::rankId
        set(blackLevel) equalToWhenPresent row::blackLevel
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun UsersBaseMapper.updateByPrimaryKey(row: UsersBase) =
    update {
        set(firstName) equalToOrNull row::firstName
        set(lastName) equalToOrNull row::lastName
        set(phoneNumber) equalToOrNull row::phoneNumber
        set(email) equalToOrNull row::email
        set(password) equalToOrNull row::password
        set(birthday) equalToOrNull row::birthday
        set(rankId) equalToOrNull row::rankId
        set(blackLevel) equalToOrNull row::blackLevel
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where { userId isEqualTo row.userId!! }
    }

fun UsersBaseMapper.updateByPrimaryKeySelective(row: UsersBase) =
    update {
        set(firstName) equalToWhenPresent row::firstName
        set(lastName) equalToWhenPresent row::lastName
        set(phoneNumber) equalToWhenPresent row::phoneNumber
        set(email) equalToWhenPresent row::email
        set(password) equalToWhenPresent row::password
        set(birthday) equalToWhenPresent row::birthday
        set(rankId) equalToWhenPresent row::rankId
        set(blackLevel) equalToWhenPresent row::blackLevel
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where { userId isEqualTo row.userId!! }
    }