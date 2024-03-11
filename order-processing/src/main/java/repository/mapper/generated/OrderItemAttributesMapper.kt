/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-03-11T09:10:07.207111+09:00
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
import repository.entity.generated.OrderItemAttributes
import repository.mapper.generated.OrderItemAttributesDynamicSqlSupport.attributeId
import repository.mapper.generated.OrderItemAttributesDynamicSqlSupport.createdAt
import repository.mapper.generated.OrderItemAttributesDynamicSqlSupport.itemId
import repository.mapper.generated.OrderItemAttributesDynamicSqlSupport.orderId
import repository.mapper.generated.OrderItemAttributesDynamicSqlSupport.orderItemAttributes
import repository.mapper.generated.OrderItemAttributesDynamicSqlSupport.updatedAt

@Mapper
interface OrderItemAttributesMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<OrderItemAttributes>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="OrderItemAttributesResult", value = [
        Result(column="order_id", property="orderId", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="item_id", property="itemId", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="attribute_id", property="attributeId", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<OrderItemAttributes>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("OrderItemAttributesResult")
    fun selectOne(selectStatement: SelectStatementProvider): OrderItemAttributes?
}

fun OrderItemAttributesMapper.count(completer: CountCompleter) =
    countFrom(this::count, orderItemAttributes, completer)

fun OrderItemAttributesMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, orderItemAttributes, completer)

fun OrderItemAttributesMapper.deleteByPrimaryKey(orderId_: Int, itemId_: Int, attributeId_: Int) =
    delete {
        where {
            orderId isEqualTo orderId_
            and { itemId isEqualTo itemId_ }
            and { attributeId isEqualTo attributeId_ }
        }
    }

fun OrderItemAttributesMapper.insert(row: OrderItemAttributes) =
    insert(this::insert, row, orderItemAttributes) {
        map(orderId) toProperty "orderId"
        map(itemId) toProperty "itemId"
        map(attributeId) toProperty "attributeId"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun OrderItemAttributesMapper.insertMultiple(records: Collection<OrderItemAttributes>) =
    insertMultiple(this::insertMultiple, records, orderItemAttributes) {
        map(orderId) toProperty "orderId"
        map(itemId) toProperty "itemId"
        map(attributeId) toProperty "attributeId"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun OrderItemAttributesMapper.insertMultiple(vararg records: OrderItemAttributes) =
    insertMultiple(records.toList())

fun OrderItemAttributesMapper.insertSelective(row: OrderItemAttributes) =
    insert(this::insert, row, orderItemAttributes) {
        map(orderId).toPropertyWhenPresent("orderId", row::orderId)
        map(itemId).toPropertyWhenPresent("itemId", row::itemId)
        map(attributeId).toPropertyWhenPresent("attributeId", row::attributeId)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(orderId, itemId, attributeId, createdAt, updatedAt)

fun OrderItemAttributesMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, orderItemAttributes, completer)

fun OrderItemAttributesMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, orderItemAttributes, completer)

fun OrderItemAttributesMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, orderItemAttributes, completer)

fun OrderItemAttributesMapper.selectByPrimaryKey(orderId_: Int, itemId_: Int, attributeId_: Int) =
    selectOne {
        where {
            orderId isEqualTo orderId_
            and { itemId isEqualTo itemId_ }
            and { attributeId isEqualTo attributeId_ }
        }
    }

fun OrderItemAttributesMapper.update(completer: UpdateCompleter) =
    update(this::update, orderItemAttributes, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: OrderItemAttributes) =
    apply {
        set(orderId) equalToOrNull row::orderId
        set(itemId) equalToOrNull row::itemId
        set(attributeId) equalToOrNull row::attributeId
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: OrderItemAttributes) =
    apply {
        set(orderId) equalToWhenPresent row::orderId
        set(itemId) equalToWhenPresent row::itemId
        set(attributeId) equalToWhenPresent row::attributeId
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun OrderItemAttributesMapper.updateByPrimaryKey(row: OrderItemAttributes) =
    update {
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where {
            orderId isEqualTo row.orderId!!
            and { itemId isEqualTo row.itemId!! }
            and { attributeId isEqualTo row.attributeId!! }
        }
    }

fun OrderItemAttributesMapper.updateByPrimaryKeySelective(row: OrderItemAttributes) =
    update {
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where {
            orderId isEqualTo row.orderId!!
            and { itemId isEqualTo row.itemId!! }
            and { attributeId isEqualTo row.attributeId!! }
        }
    }