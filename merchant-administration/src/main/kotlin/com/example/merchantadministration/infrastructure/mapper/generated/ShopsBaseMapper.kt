/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.merchantadministration.infrastructure.mapper.generated

import com.example.merchantadministration.infrastructure.entity.generated.ShopsBase
import com.example.merchantadministration.infrastructure.mapper.generated.ShopsBaseDynamicSqlSupport.chainId
import com.example.merchantadministration.infrastructure.mapper.generated.ShopsBaseDynamicSqlSupport.createdAt
import com.example.merchantadministration.infrastructure.mapper.generated.ShopsBaseDynamicSqlSupport.name
import com.example.merchantadministration.infrastructure.mapper.generated.ShopsBaseDynamicSqlSupport.shopId
import com.example.merchantadministration.infrastructure.mapper.generated.ShopsBaseDynamicSqlSupport.shopsBase
import com.example.merchantadministration.infrastructure.mapper.generated.ShopsBaseDynamicSqlSupport.updatedAt
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
interface ShopsBaseMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<ShopsBase>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="ShopsBaseResult", value = [
        Result(column="shop_id", property="shopId", jdbcType=JdbcType.BIGINT, id=true),
        Result(column="chain_id", property="chainId", jdbcType=JdbcType.BIGINT),
        Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<ShopsBase>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("ShopsBaseResult")
    fun selectOne(selectStatement: SelectStatementProvider): ShopsBase?
}

fun ShopsBaseMapper.count(completer: CountCompleter) =
    countFrom(this::count, shopsBase, completer)

fun ShopsBaseMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, shopsBase, completer)

fun ShopsBaseMapper.deleteByPrimaryKey(shopId_: Long) =
    delete {
        where { shopId isEqualTo shopId_ }
    }

fun ShopsBaseMapper.insert(row: ShopsBase) =
    insert(this::insert, row, shopsBase) {
        map(shopId) toProperty "shopId"
        map(chainId) toProperty "chainId"
        map(name) toProperty "name"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun ShopsBaseMapper.insertMultiple(records: Collection<ShopsBase>) =
    insertMultiple(this::insertMultiple, records, shopsBase) {
        map(shopId) toProperty "shopId"
        map(chainId) toProperty "chainId"
        map(name) toProperty "name"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun ShopsBaseMapper.insertMultiple(vararg records: ShopsBase) =
    insertMultiple(records.toList())

fun ShopsBaseMapper.insertSelective(row: ShopsBase) =
    insert(this::insert, row, shopsBase) {
        map(shopId).toPropertyWhenPresent("shopId", row::shopId)
        map(chainId).toPropertyWhenPresent("chainId", row::chainId)
        map(name).toPropertyWhenPresent("name", row::name)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(shopId, chainId, name, createdAt, updatedAt)

fun ShopsBaseMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, shopsBase, completer)

fun ShopsBaseMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, shopsBase, completer)

fun ShopsBaseMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, shopsBase, completer)

fun ShopsBaseMapper.selectByPrimaryKey(shopId_: Long) =
    selectOne {
        where { shopId isEqualTo shopId_ }
    }

fun ShopsBaseMapper.update(completer: UpdateCompleter) =
    update(this::update, shopsBase, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: ShopsBase) =
    apply {
        set(shopId) equalToOrNull row::shopId
        set(chainId) equalToOrNull row::chainId
        set(name) equalToOrNull row::name
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: ShopsBase) =
    apply {
        set(shopId) equalToWhenPresent row::shopId
        set(chainId) equalToWhenPresent row::chainId
        set(name) equalToWhenPresent row::name
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun ShopsBaseMapper.updateByPrimaryKey(row: ShopsBase) =
    update {
        set(chainId) equalToOrNull row::chainId
        set(name) equalToOrNull row::name
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where { shopId isEqualTo row.shopId!! }
    }

fun ShopsBaseMapper.updateByPrimaryKeySelective(row: ShopsBase) =
    update {
        set(chainId) equalToWhenPresent row::chainId
        set(name) equalToWhenPresent row::name
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where { shopId isEqualTo row.shopId!! }
    }