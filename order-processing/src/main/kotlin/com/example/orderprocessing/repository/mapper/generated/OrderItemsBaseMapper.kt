/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.repository.mapper.generated

import com.example.orderprocessing.repository.entity.generated.OrderItemsBase
import com.example.orderprocessing.repository.mapper.generated.OrderItemsBaseDynamicSqlSupport.createdAt
import com.example.orderprocessing.repository.mapper.generated.OrderItemsBaseDynamicSqlSupport.itemId
import com.example.orderprocessing.repository.mapper.generated.OrderItemsBaseDynamicSqlSupport.orderId
import com.example.orderprocessing.repository.mapper.generated.OrderItemsBaseDynamicSqlSupport.orderItemsBase
import com.example.orderprocessing.repository.mapper.generated.OrderItemsBaseDynamicSqlSupport.quantity
import com.example.orderprocessing.repository.mapper.generated.OrderItemsBaseDynamicSqlSupport.updatedAt
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
interface OrderItemsBaseMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<OrderItemsBase>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="OrderItemsBaseResult", value = [
        Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT, id=true),
        Result(column="item_id", property="itemId", jdbcType=JdbcType.BIGINT, id=true),
        Result(column="quantity", property="quantity", jdbcType=JdbcType.INTEGER),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<OrderItemsBase>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("OrderItemsBaseResult")
    fun selectOne(selectStatement: SelectStatementProvider): OrderItemsBase?
}

fun OrderItemsBaseMapper.count(completer: CountCompleter) =
    countFrom(this::count, orderItemsBase, completer)

fun OrderItemsBaseMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, orderItemsBase, completer)

fun OrderItemsBaseMapper.deleteByPrimaryKey(orderId_: Long, itemId_: Long) =
    delete {
        where {
            orderId isEqualTo orderId_
            and { itemId isEqualTo itemId_ }
        }
    }

fun OrderItemsBaseMapper.insert(row: OrderItemsBase) =
    insert(this::insert, row, orderItemsBase) {
        map(orderId) toProperty "orderId"
        map(itemId) toProperty "itemId"
        map(quantity) toProperty "quantity"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun OrderItemsBaseMapper.insertMultiple(records: Collection<OrderItemsBase>) =
    insertMultiple(this::insertMultiple, records, orderItemsBase) {
        map(orderId) toProperty "orderId"
        map(itemId) toProperty "itemId"
        map(quantity) toProperty "quantity"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun OrderItemsBaseMapper.insertMultiple(vararg records: OrderItemsBase) =
    insertMultiple(records.toList())

fun OrderItemsBaseMapper.insertSelective(row: OrderItemsBase) =
    insert(this::insert, row, orderItemsBase) {
        map(orderId).toPropertyWhenPresent("orderId", row::orderId)
        map(itemId).toPropertyWhenPresent("itemId", row::itemId)
        map(quantity).toPropertyWhenPresent("quantity", row::quantity)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(orderId, itemId, quantity, createdAt, updatedAt)

fun OrderItemsBaseMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, orderItemsBase, completer)

fun OrderItemsBaseMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, orderItemsBase, completer)

fun OrderItemsBaseMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, orderItemsBase, completer)

fun OrderItemsBaseMapper.selectByPrimaryKey(orderId_: Long, itemId_: Long) =
    selectOne {
        where {
            orderId isEqualTo orderId_
            and { itemId isEqualTo itemId_ }
        }
    }

fun OrderItemsBaseMapper.update(completer: UpdateCompleter) =
    update(this::update, orderItemsBase, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: OrderItemsBase) =
    apply {
        set(orderId) equalToOrNull row::orderId
        set(itemId) equalToOrNull row::itemId
        set(quantity) equalToOrNull row::quantity
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: OrderItemsBase) =
    apply {
        set(orderId) equalToWhenPresent row::orderId
        set(itemId) equalToWhenPresent row::itemId
        set(quantity) equalToWhenPresent row::quantity
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun OrderItemsBaseMapper.updateByPrimaryKey(row: OrderItemsBase) =
    update {
        set(quantity) equalToOrNull row::quantity
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where {
            orderId isEqualTo row.orderId!!
            and { itemId isEqualTo row.itemId!! }
        }
    }

fun OrderItemsBaseMapper.updateByPrimaryKeySelective(row: OrderItemsBase) =
    update {
        set(quantity) equalToWhenPresent row::quantity
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where {
            orderId isEqualTo row.orderId!!
            and { itemId isEqualTo row.itemId!! }
        }
    }