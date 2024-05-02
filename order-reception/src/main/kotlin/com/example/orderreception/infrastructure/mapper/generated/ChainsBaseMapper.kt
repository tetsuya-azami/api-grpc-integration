/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderreception.infrastructure.mapper.generated

import com.example.orderreception.infrastructure.entity.generated.ChainsBase
import com.example.orderreception.infrastructure.mapper.generated.ChainsBaseDynamicSqlSupport.chainId
import com.example.orderreception.infrastructure.mapper.generated.ChainsBaseDynamicSqlSupport.chainsBase
import com.example.orderreception.infrastructure.mapper.generated.ChainsBaseDynamicSqlSupport.createdAt
import com.example.orderreception.infrastructure.mapper.generated.ChainsBaseDynamicSqlSupport.name
import com.example.orderreception.infrastructure.mapper.generated.ChainsBaseDynamicSqlSupport.updatedAt
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
interface ChainsBaseMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<ChainsBase>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="ChainsBaseResult", value = [
        Result(column="chain_id", property="chainId", jdbcType=JdbcType.BIGINT, id=true),
        Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<ChainsBase>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("ChainsBaseResult")
    fun selectOne(selectStatement: SelectStatementProvider): ChainsBase?
}

fun ChainsBaseMapper.count(completer: CountCompleter) =
    countFrom(this::count, chainsBase, completer)

fun ChainsBaseMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, chainsBase, completer)

fun ChainsBaseMapper.deleteByPrimaryKey(chainId_: Long) =
    delete {
        where { chainId isEqualTo chainId_ }
    }

fun ChainsBaseMapper.insert(row: ChainsBase) =
    insert(this::insert, row, chainsBase) {
        map(chainId) toProperty "chainId"
        map(name) toProperty "name"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun ChainsBaseMapper.insertMultiple(records: Collection<ChainsBase>) =
    insertMultiple(this::insertMultiple, records, chainsBase) {
        map(chainId) toProperty "chainId"
        map(name) toProperty "name"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun ChainsBaseMapper.insertMultiple(vararg records: ChainsBase) =
    insertMultiple(records.toList())

fun ChainsBaseMapper.insertSelective(row: ChainsBase) =
    insert(this::insert, row, chainsBase) {
        map(chainId).toPropertyWhenPresent("chainId", row::chainId)
        map(name).toPropertyWhenPresent("name", row::name)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(chainId, name, createdAt, updatedAt)

fun ChainsBaseMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, chainsBase, completer)

fun ChainsBaseMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, chainsBase, completer)

fun ChainsBaseMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, chainsBase, completer)

fun ChainsBaseMapper.selectByPrimaryKey(chainId_: Long) =
    selectOne {
        where { chainId isEqualTo chainId_ }
    }

fun ChainsBaseMapper.update(completer: UpdateCompleter) =
    update(this::update, chainsBase, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: ChainsBase) =
    apply {
        set(chainId) equalToOrNull row::chainId
        set(name) equalToOrNull row::name
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: ChainsBase) =
    apply {
        set(chainId) equalToWhenPresent row::chainId
        set(name) equalToWhenPresent row::name
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun ChainsBaseMapper.updateByPrimaryKey(row: ChainsBase) =
    update {
        set(name) equalToOrNull row::name
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where { chainId isEqualTo row.chainId!! }
    }

fun ChainsBaseMapper.updateByPrimaryKeySelective(row: ChainsBase) =
    update {
        set(name) equalToWhenPresent row::name
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where { chainId isEqualTo row.chainId!! }
    }