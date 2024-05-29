/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.merchantadministration.infrastructure.mapper.generated

import com.example.merchantadministration.infrastructure.entity.generated.AttributesBase
import com.example.merchantadministration.infrastructure.mapper.generated.AttributesBaseDynamicSqlSupport.attributeId
import com.example.merchantadministration.infrastructure.mapper.generated.AttributesBaseDynamicSqlSupport.attributesBase
import com.example.merchantadministration.infrastructure.mapper.generated.AttributesBaseDynamicSqlSupport.createdAt
import com.example.merchantadministration.infrastructure.mapper.generated.AttributesBaseDynamicSqlSupport.name
import com.example.merchantadministration.infrastructure.mapper.generated.AttributesBaseDynamicSqlSupport.updatedAt
import com.example.merchantadministration.infrastructure.mapper.generated.AttributesBaseDynamicSqlSupport.value
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
interface AttributesBaseMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<AttributesBase>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="AttributesBaseResult", value = [
        Result(column="attribute_id", property="attributeId", jdbcType=JdbcType.BIGINT, id=true),
        Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<AttributesBase>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("AttributesBaseResult")
    fun selectOne(selectStatement: SelectStatementProvider): AttributesBase?
}

fun AttributesBaseMapper.count(completer: CountCompleter) =
    countFrom(this::count, attributesBase, completer)

fun AttributesBaseMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, attributesBase, completer)

fun AttributesBaseMapper.deleteByPrimaryKey(attributeId_: Long) =
    delete {
        where { attributeId isEqualTo attributeId_ }
    }

fun AttributesBaseMapper.insert(row: AttributesBase) =
    insert(this::insert, row, attributesBase) {
        map(attributeId) toProperty "attributeId"
        map(name) toProperty "name"
        map(value) toProperty "value"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun AttributesBaseMapper.insertMultiple(records: Collection<AttributesBase>) =
    insertMultiple(this::insertMultiple, records, attributesBase) {
        map(attributeId) toProperty "attributeId"
        map(name) toProperty "name"
        map(value) toProperty "value"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun AttributesBaseMapper.insertMultiple(vararg records: AttributesBase) =
    insertMultiple(records.toList())

fun AttributesBaseMapper.insertSelective(row: AttributesBase) =
    insert(this::insert, row, attributesBase) {
        map(attributeId).toPropertyWhenPresent("attributeId", row::attributeId)
        map(name).toPropertyWhenPresent("name", row::name)
        map(value).toPropertyWhenPresent("value", row::value)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(attributeId, name, value, createdAt, updatedAt)

fun AttributesBaseMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, attributesBase, completer)

fun AttributesBaseMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, attributesBase, completer)

fun AttributesBaseMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, attributesBase, completer)

fun AttributesBaseMapper.selectByPrimaryKey(attributeId_: Long) =
    selectOne {
        where { attributeId isEqualTo attributeId_ }
    }

fun AttributesBaseMapper.update(completer: UpdateCompleter) =
    update(this::update, attributesBase, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: AttributesBase) =
    apply {
        set(attributeId) equalToOrNull row::attributeId
        set(name) equalToOrNull row::name
        set(value) equalToOrNull row::value
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: AttributesBase) =
    apply {
        set(attributeId) equalToWhenPresent row::attributeId
        set(name) equalToWhenPresent row::name
        set(value) equalToWhenPresent row::value
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun AttributesBaseMapper.updateByPrimaryKey(row: AttributesBase) =
    update {
        set(name) equalToOrNull row::name
        set(value) equalToOrNull row::value
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where { attributeId isEqualTo row.attributeId!! }
    }

fun AttributesBaseMapper.updateByPrimaryKeySelective(row: AttributesBase) =
    update {
        set(name) equalToWhenPresent row::name
        set(value) equalToWhenPresent row::value
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where { attributeId isEqualTo row.attributeId!! }
    }