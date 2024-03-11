/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-03-11T09:23:44.88749+09:00
 */
package com.example.orderprocessing.repository.mapper.generated

import com.example.orderprocessing.repository.entity.generated.Attributes
import com.example.orderprocessing.repository.mapper.generated.AttributesDynamicSqlSupport.attributeId
import com.example.orderprocessing.repository.mapper.generated.AttributesDynamicSqlSupport.attributes
import com.example.orderprocessing.repository.mapper.generated.AttributesDynamicSqlSupport.createdAt
import com.example.orderprocessing.repository.mapper.generated.AttributesDynamicSqlSupport.name
import com.example.orderprocessing.repository.mapper.generated.AttributesDynamicSqlSupport.updatedAt
import com.example.orderprocessing.repository.mapper.generated.AttributesDynamicSqlSupport.value
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
interface AttributesMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Attributes>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="AttributesResult", value = [
        Result(column="attribute_id", property="attributeId", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<Attributes>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("AttributesResult")
    fun selectOne(selectStatement: SelectStatementProvider): Attributes?
}

fun AttributesMapper.count(completer: CountCompleter) =
    countFrom(this::count, attributes, completer)

fun AttributesMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, attributes, completer)

fun AttributesMapper.deleteByPrimaryKey(attributeId_: Int) =
    delete {
        where { attributeId isEqualTo attributeId_ }
    }

fun AttributesMapper.insert(row: Attributes) =
    insert(this::insert, row, attributes) {
        map(attributeId) toProperty "attributeId"
        map(name) toProperty "name"
        map(value) toProperty "value"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun AttributesMapper.insertMultiple(records: Collection<Attributes>) =
    insertMultiple(this::insertMultiple, records, attributes) {
        map(attributeId) toProperty "attributeId"
        map(name) toProperty "name"
        map(value) toProperty "value"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun AttributesMapper.insertMultiple(vararg records: Attributes) =
    insertMultiple(records.toList())

fun AttributesMapper.insertSelective(row: Attributes) =
    insert(this::insert, row, attributes) {
        map(attributeId).toPropertyWhenPresent("attributeId", row::attributeId)
        map(name).toPropertyWhenPresent("name", row::name)
        map(value).toPropertyWhenPresent("value", row::value)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(attributeId, name, value, createdAt, updatedAt)

fun AttributesMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, attributes, completer)

fun AttributesMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, attributes, completer)

fun AttributesMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, attributes, completer)

fun AttributesMapper.selectByPrimaryKey(attributeId_: Int) =
    selectOne {
        where { attributeId isEqualTo attributeId_ }
    }

fun AttributesMapper.update(completer: UpdateCompleter) =
    update(this::update, attributes, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: Attributes) =
    apply {
        set(attributeId) equalToOrNull row::attributeId
        set(name) equalToOrNull row::name
        set(value) equalToOrNull row::value
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: Attributes) =
    apply {
        set(attributeId) equalToWhenPresent row::attributeId
        set(name) equalToWhenPresent row::name
        set(value) equalToWhenPresent row::value
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun AttributesMapper.updateByPrimaryKey(row: Attributes) =
    update {
        set(name) equalToOrNull row::name
        set(value) equalToOrNull row::value
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where { attributeId isEqualTo row.attributeId!! }
    }

fun AttributesMapper.updateByPrimaryKeySelective(row: Attributes) =
    update {
        set(name) equalToWhenPresent row::name
        set(value) equalToWhenPresent row::value
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where { attributeId isEqualTo row.attributeId!! }
    }