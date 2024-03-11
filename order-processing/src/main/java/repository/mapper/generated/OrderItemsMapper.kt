/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-03-11T09:10:07.20367+09:00
 */
package repository.mapper.generated

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
import repository.entity.generated.OrderItems
import repository.mapper.generated.OrderItemsDynamicSqlSupport.createdAt
import repository.mapper.generated.OrderItemsDynamicSqlSupport.itemId
import repository.mapper.generated.OrderItemsDynamicSqlSupport.orderId
import repository.mapper.generated.OrderItemsDynamicSqlSupport.orderItems
import repository.mapper.generated.OrderItemsDynamicSqlSupport.quantity
import repository.mapper.generated.OrderItemsDynamicSqlSupport.updatedAt

@Mapper
interface OrderItemsMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<OrderItems>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="OrderItemsResult", value = [
        Result(column="order_id", property="orderId", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="item_id", property="itemId", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="quantity", property="quantity", jdbcType=JdbcType.INTEGER),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<OrderItems>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("OrderItemsResult")
    fun selectOne(selectStatement: SelectStatementProvider): OrderItems?
}

fun OrderItemsMapper.count(completer: CountCompleter) =
    countFrom(this::count, orderItems, completer)

fun OrderItemsMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, orderItems, completer)

fun OrderItemsMapper.deleteByPrimaryKey(orderId_: Int, itemId_: Int) =
    delete {
        where {
            orderId isEqualTo orderId_
            and { itemId isEqualTo itemId_ }
        }
    }

fun OrderItemsMapper.insert(row: OrderItems) =
    insert(this::insert, row, orderItems) {
        map(orderId) toProperty "orderId"
        map(itemId) toProperty "itemId"
        map(quantity) toProperty "quantity"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun OrderItemsMapper.insertMultiple(records: Collection<OrderItems>) =
    insertMultiple(this::insertMultiple, records, orderItems) {
        map(orderId) toProperty "orderId"
        map(itemId) toProperty "itemId"
        map(quantity) toProperty "quantity"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun OrderItemsMapper.insertMultiple(vararg records: OrderItems) =
    insertMultiple(records.toList())

fun OrderItemsMapper.insertSelective(row: OrderItems) =
    insert(this::insert, row, orderItems) {
        map(orderId).toPropertyWhenPresent("orderId", row::orderId)
        map(itemId).toPropertyWhenPresent("itemId", row::itemId)
        map(quantity).toPropertyWhenPresent("quantity", row::quantity)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(orderId, itemId, quantity, createdAt, updatedAt)

fun OrderItemsMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, orderItems, completer)

fun OrderItemsMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, orderItems, completer)

fun OrderItemsMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, orderItems, completer)

fun OrderItemsMapper.selectByPrimaryKey(orderId_: Int, itemId_: Int) =
    selectOne {
        where {
            orderId isEqualTo orderId_
            and { itemId isEqualTo itemId_ }
        }
    }

fun OrderItemsMapper.update(completer: UpdateCompleter) =
    update(this::update, orderItems, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: OrderItems) =
    apply {
        set(orderId) equalToOrNull row::orderId
        set(itemId) equalToOrNull row::itemId
        set(quantity) equalToOrNull row::quantity
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: OrderItems) =
    apply {
        set(orderId) equalToWhenPresent row::orderId
        set(itemId) equalToWhenPresent row::itemId
        set(quantity) equalToWhenPresent row::quantity
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun OrderItemsMapper.updateByPrimaryKey(row: OrderItems) =
    update {
        set(quantity) equalToOrNull row::quantity
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where {
            orderId isEqualTo row.orderId!!
            and { itemId isEqualTo row.itemId!! }
        }
    }

fun OrderItemsMapper.updateByPrimaryKeySelective(row: OrderItems) =
    update {
        set(quantity) equalToWhenPresent row::quantity
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where {
            orderId isEqualTo row.orderId!!
            and { itemId isEqualTo row.itemId!! }
        }
    }