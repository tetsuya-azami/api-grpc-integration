/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderreception.infrastructure.mapper.generated

import com.example.orderreception.infrastructure.entity.generated.UsersBase
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseDynamicSqlSupport.currentConnections
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseDynamicSqlSupport.maxSessionControlledMemory
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseDynamicSqlSupport.maxSessionTotalMemory
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseDynamicSqlSupport.totalConnections
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseDynamicSqlSupport.user
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
        Result(column="USER", property="user", jdbcType=JdbcType.CHAR),
        Result(column="CURRENT_CONNECTIONS", property="currentConnections", jdbcType=JdbcType.BIGINT),
        Result(column="TOTAL_CONNECTIONS", property="totalConnections", jdbcType=JdbcType.BIGINT),
        Result(column="MAX_SESSION_CONTROLLED_MEMORY", property="maxSessionControlledMemory", jdbcType=JdbcType.BIGINT),
        Result(column="MAX_SESSION_TOTAL_MEMORY", property="maxSessionTotalMemory", jdbcType=JdbcType.BIGINT)
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

fun UsersBaseMapper.insert(row: UsersBase) =
    insert(this::insert, row, usersBase) {
        map(user) toProperty "user"
        map(currentConnections) toProperty "currentConnections"
        map(totalConnections) toProperty "totalConnections"
        map(maxSessionControlledMemory) toProperty "maxSessionControlledMemory"
        map(maxSessionTotalMemory) toProperty "maxSessionTotalMemory"
    }

fun UsersBaseMapper.insertMultiple(records: Collection<UsersBase>) =
    insertMultiple(this::insertMultiple, records, usersBase) {
        map(user) toProperty "user"
        map(currentConnections) toProperty "currentConnections"
        map(totalConnections) toProperty "totalConnections"
        map(maxSessionControlledMemory) toProperty "maxSessionControlledMemory"
        map(maxSessionTotalMemory) toProperty "maxSessionTotalMemory"
    }

fun UsersBaseMapper.insertMultiple(vararg records: UsersBase) =
    insertMultiple(records.toList())

fun UsersBaseMapper.insertSelective(row: UsersBase) =
    insert(this::insert, row, usersBase) {
        map(user).toPropertyWhenPresent("user", row::user)
        map(currentConnections).toPropertyWhenPresent("currentConnections", row::currentConnections)
        map(totalConnections).toPropertyWhenPresent("totalConnections", row::totalConnections)
        map(maxSessionControlledMemory).toPropertyWhenPresent("maxSessionControlledMemory", row::maxSessionControlledMemory)
        map(maxSessionTotalMemory).toPropertyWhenPresent("maxSessionTotalMemory", row::maxSessionTotalMemory)
    }

private val columnList = listOf(user, currentConnections, totalConnections, maxSessionControlledMemory, maxSessionTotalMemory)

fun UsersBaseMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, usersBase, completer)

fun UsersBaseMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, usersBase, completer)

fun UsersBaseMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, usersBase, completer)

fun UsersBaseMapper.update(completer: UpdateCompleter) =
    update(this::update, usersBase, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: UsersBase) =
    apply {
        set(user) equalToOrNull row::user
        set(currentConnections) equalToOrNull row::currentConnections
        set(totalConnections) equalToOrNull row::totalConnections
        set(maxSessionControlledMemory) equalToOrNull row::maxSessionControlledMemory
        set(maxSessionTotalMemory) equalToOrNull row::maxSessionTotalMemory
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: UsersBase) =
    apply {
        set(user) equalToWhenPresent row::user
        set(currentConnections) equalToWhenPresent row::currentConnections
        set(totalConnections) equalToWhenPresent row::totalConnections
        set(maxSessionControlledMemory) equalToWhenPresent row::maxSessionControlledMemory
        set(maxSessionTotalMemory) equalToWhenPresent row::maxSessionTotalMemory
    }