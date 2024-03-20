/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.repository.mapper.generated

import com.example.orderprocessing.repository.entity.generated.OrderItemAttributesBase
import com.example.orderprocessing.repository.mapper.generated.OrderItemAttributesBaseDynamicSqlSupport.attributeId
import com.example.orderprocessing.repository.mapper.generated.OrderItemAttributesBaseDynamicSqlSupport.createdAt
import com.example.orderprocessing.repository.mapper.generated.OrderItemAttributesBaseDynamicSqlSupport.itemId
import com.example.orderprocessing.repository.mapper.generated.OrderItemAttributesBaseDynamicSqlSupport.orderId
import com.example.orderprocessing.repository.mapper.generated.OrderItemAttributesBaseDynamicSqlSupport.orderItemAttributesBase
import com.example.orderprocessing.repository.mapper.generated.OrderItemAttributesBaseDynamicSqlSupport.updatedAt
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
interface OrderItemAttributesBaseMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<OrderItemAttributesBase>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="OrderItemAttributesBaseResult", value = [
        Result(column="order_id", property="orderId", jdbcType=JdbcType.VARCHAR, id=true),
        Result(column="item_id", property="itemId", jdbcType=JdbcType.BIGINT, id=true),
        Result(column="attribute_id", property="attributeId", jdbcType=JdbcType.BIGINT, id=true),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<OrderItemAttributesBase>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("OrderItemAttributesBaseResult")
    fun selectOne(selectStatement: SelectStatementProvider): OrderItemAttributesBase?
}

fun OrderItemAttributesBaseMapper.count(completer: CountCompleter) =
    countFrom(this::count, orderItemAttributesBase, completer)

fun OrderItemAttributesBaseMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, orderItemAttributesBase, completer)

fun OrderItemAttributesBaseMapper.deleteByPrimaryKey(orderId_: String, itemId_: Long, attributeId_: Long) =
    delete {
        where {
            orderId isEqualTo orderId_
            and { itemId isEqualTo itemId_ }
            and { attributeId isEqualTo attributeId_ }
        }
    }

fun OrderItemAttributesBaseMapper.insert(row: OrderItemAttributesBase) =
    insert(this::insert, row, orderItemAttributesBase) {
        map(orderId) toProperty "orderId"
        map(itemId) toProperty "itemId"
        map(attributeId) toProperty "attributeId"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun OrderItemAttributesBaseMapper.insertMultiple(records: Collection<OrderItemAttributesBase>) =
    insertMultiple(this::insertMultiple, records, orderItemAttributesBase) {
        map(orderId) toProperty "orderId"
        map(itemId) toProperty "itemId"
        map(attributeId) toProperty "attributeId"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun OrderItemAttributesBaseMapper.insertMultiple(vararg records: OrderItemAttributesBase) =
    insertMultiple(records.toList())

fun OrderItemAttributesBaseMapper.insertSelective(row: OrderItemAttributesBase) =
    insert(this::insert, row, orderItemAttributesBase) {
        map(orderId).toPropertyWhenPresent("orderId", row::orderId)
        map(itemId).toPropertyWhenPresent("itemId", row::itemId)
        map(attributeId).toPropertyWhenPresent("attributeId", row::attributeId)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(orderId, itemId, attributeId, createdAt, updatedAt)

fun OrderItemAttributesBaseMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, orderItemAttributesBase, completer)

fun OrderItemAttributesBaseMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, orderItemAttributesBase, completer)

fun OrderItemAttributesBaseMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, orderItemAttributesBase, completer)

fun OrderItemAttributesBaseMapper.selectByPrimaryKey(orderId_: String, itemId_: Long, attributeId_: Long) =
    selectOne {
        where {
            orderId isEqualTo orderId_
            and { itemId isEqualTo itemId_ }
            and { attributeId isEqualTo attributeId_ }
        }
    }

fun OrderItemAttributesBaseMapper.update(completer: UpdateCompleter) =
    update(this::update, orderItemAttributesBase, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: OrderItemAttributesBase) =
    apply {
        set(orderId) equalToOrNull row::orderId
        set(itemId) equalToOrNull row::itemId
        set(attributeId) equalToOrNull row::attributeId
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: OrderItemAttributesBase) =
    apply {
        set(orderId) equalToWhenPresent row::orderId
        set(itemId) equalToWhenPresent row::itemId
        set(attributeId) equalToWhenPresent row::attributeId
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun OrderItemAttributesBaseMapper.updateByPrimaryKey(row: OrderItemAttributesBase) =
    update {
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where {
            orderId isEqualTo row.orderId!!
            and { itemId isEqualTo row.itemId!! }
            and { attributeId isEqualTo row.attributeId!! }
        }
    }

fun OrderItemAttributesBaseMapper.updateByPrimaryKeySelective(row: OrderItemAttributesBase) =
    update {
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where {
            orderId isEqualTo row.orderId!!
            and { itemId isEqualTo row.itemId!! }
            and { attributeId isEqualTo row.attributeId!! }
        }
    }