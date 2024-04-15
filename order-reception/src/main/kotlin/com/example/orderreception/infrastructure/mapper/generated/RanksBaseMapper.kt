/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderreception.infrastructure.mapper.generated

import com.example.orderreception.infrastructure.entity.generated.RanksBase
import com.example.orderreception.infrastructure.mapper.generated.RanksBaseDynamicSqlSupport.createdAt
import com.example.orderreception.infrastructure.mapper.generated.RanksBaseDynamicSqlSupport.name
import com.example.orderreception.infrastructure.mapper.generated.RanksBaseDynamicSqlSupport.rankId
import com.example.orderreception.infrastructure.mapper.generated.RanksBaseDynamicSqlSupport.ranksBase
import com.example.orderreception.infrastructure.mapper.generated.RanksBaseDynamicSqlSupport.updatedAt
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
interface RanksBaseMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<RanksBase>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="RanksBaseResult", value = [
        Result(column="rank_id", property="rankId", jdbcType=JdbcType.BIGINT, id=true),
        Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<RanksBase>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("RanksBaseResult")
    fun selectOne(selectStatement: SelectStatementProvider): RanksBase?
}

fun RanksBaseMapper.count(completer: CountCompleter) =
    countFrom(this::count, ranksBase, completer)

fun RanksBaseMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, ranksBase, completer)

fun RanksBaseMapper.deleteByPrimaryKey(rankId_: Long) =
    delete {
        where { rankId isEqualTo rankId_ }
    }

fun RanksBaseMapper.insert(row: RanksBase) =
    insert(this::insert, row, ranksBase) {
        map(rankId) toProperty "rankId"
        map(name) toProperty "name"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun RanksBaseMapper.insertMultiple(records: Collection<RanksBase>) =
    insertMultiple(this::insertMultiple, records, ranksBase) {
        map(rankId) toProperty "rankId"
        map(name) toProperty "name"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun RanksBaseMapper.insertMultiple(vararg records: RanksBase) =
    insertMultiple(records.toList())

fun RanksBaseMapper.insertSelective(row: RanksBase) =
    insert(this::insert, row, ranksBase) {
        map(rankId).toPropertyWhenPresent("rankId", row::rankId)
        map(name).toPropertyWhenPresent("name", row::name)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(rankId, name, createdAt, updatedAt)

fun RanksBaseMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, ranksBase, completer)

fun RanksBaseMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, ranksBase, completer)

fun RanksBaseMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, ranksBase, completer)

fun RanksBaseMapper.selectByPrimaryKey(rankId_: Long) =
    selectOne {
        where { rankId isEqualTo rankId_ }
    }

fun RanksBaseMapper.update(completer: UpdateCompleter) =
    update(this::update, ranksBase, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: RanksBase) =
    apply {
        set(rankId) equalToOrNull row::rankId
        set(name) equalToOrNull row::name
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: RanksBase) =
    apply {
        set(rankId) equalToWhenPresent row::rankId
        set(name) equalToWhenPresent row::name
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun RanksBaseMapper.updateByPrimaryKey(row: RanksBase) =
    update {
        set(name) equalToOrNull row::name
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where { rankId isEqualTo row.rankId!! }
    }

fun RanksBaseMapper.updateByPrimaryKeySelective(row: RanksBase) =
    update {
        set(name) equalToWhenPresent row::name
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where { rankId isEqualTo row.rankId!! }
    }