/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.repository.mapper.generated

import com.example.orderprocessing.repository.entity.generated.ItemAttributesBase
import com.example.orderprocessing.repository.mapper.generated.ItemAttributesBaseDynamicSqlSupport.attributeId
import com.example.orderprocessing.repository.mapper.generated.ItemAttributesBaseDynamicSqlSupport.createdAt
import com.example.orderprocessing.repository.mapper.generated.ItemAttributesBaseDynamicSqlSupport.itemAttributesBase
import com.example.orderprocessing.repository.mapper.generated.ItemAttributesBaseDynamicSqlSupport.itemId
import com.example.orderprocessing.repository.mapper.generated.ItemAttributesBaseDynamicSqlSupport.updatedAt
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
interface ItemAttributesBaseMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<ItemAttributesBase>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="ItemAttributesBaseResult", value = [
        Result(column="item_id", property="itemId", jdbcType=JdbcType.BIGINT, id=true),
        Result(column="attribute_id", property="attributeId", jdbcType=JdbcType.BIGINT, id=true),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<ItemAttributesBase>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("ItemAttributesBaseResult")
    fun selectOne(selectStatement: SelectStatementProvider): ItemAttributesBase?
}

fun ItemAttributesBaseMapper.count(completer: CountCompleter) =
    countFrom(this::count, itemAttributesBase, completer)

fun ItemAttributesBaseMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, itemAttributesBase, completer)

fun ItemAttributesBaseMapper.deleteByPrimaryKey(itemId_: Long, attributeId_: Long) =
    delete {
        where {
            itemId isEqualTo itemId_
            and { attributeId isEqualTo attributeId_ }
        }
    }

fun ItemAttributesBaseMapper.insert(row: ItemAttributesBase) =
    insert(this::insert, row, itemAttributesBase) {
        map(itemId) toProperty "itemId"
        map(attributeId) toProperty "attributeId"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun ItemAttributesBaseMapper.insertMultiple(records: Collection<ItemAttributesBase>) =
    insertMultiple(this::insertMultiple, records, itemAttributesBase) {
        map(itemId) toProperty "itemId"
        map(attributeId) toProperty "attributeId"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun ItemAttributesBaseMapper.insertMultiple(vararg records: ItemAttributesBase) =
    insertMultiple(records.toList())

fun ItemAttributesBaseMapper.insertSelective(row: ItemAttributesBase) =
    insert(this::insert, row, itemAttributesBase) {
        map(itemId).toPropertyWhenPresent("itemId", row::itemId)
        map(attributeId).toPropertyWhenPresent("attributeId", row::attributeId)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(itemId, attributeId, createdAt, updatedAt)

fun ItemAttributesBaseMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, itemAttributesBase, completer)

fun ItemAttributesBaseMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, itemAttributesBase, completer)

fun ItemAttributesBaseMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, itemAttributesBase, completer)

fun ItemAttributesBaseMapper.selectByPrimaryKey(itemId_: Long, attributeId_: Long) =
    selectOne {
        where {
            itemId isEqualTo itemId_
            and { attributeId isEqualTo attributeId_ }
        }
    }

fun ItemAttributesBaseMapper.update(completer: UpdateCompleter) =
    update(this::update, itemAttributesBase, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: ItemAttributesBase) =
    apply {
        set(itemId) equalToOrNull row::itemId
        set(attributeId) equalToOrNull row::attributeId
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: ItemAttributesBase) =
    apply {
        set(itemId) equalToWhenPresent row::itemId
        set(attributeId) equalToWhenPresent row::attributeId
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun ItemAttributesBaseMapper.updateByPrimaryKey(row: ItemAttributesBase) =
    update {
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where {
            itemId isEqualTo row.itemId!!
            and { attributeId isEqualTo row.attributeId!! }
        }
    }

fun ItemAttributesBaseMapper.updateByPrimaryKeySelective(row: ItemAttributesBase) =
    update {
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where {
            itemId isEqualTo row.itemId!!
            and { attributeId isEqualTo row.attributeId!! }
        }
    }