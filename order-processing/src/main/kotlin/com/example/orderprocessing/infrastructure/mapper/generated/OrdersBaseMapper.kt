/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.infrastructure.mapper.generated

import com.example.orderprocessing.infrastructure.entity.generated.OrdersBase
import com.example.orderprocessing.infrastructure.mapper.generated.OrdersBaseDynamicSqlSupport.blackLevel
import com.example.orderprocessing.infrastructure.mapper.generated.OrdersBaseDynamicSqlSupport.chainId
import com.example.orderprocessing.infrastructure.mapper.generated.OrdersBaseDynamicSqlSupport.createdAt
import com.example.orderprocessing.infrastructure.mapper.generated.OrdersBaseDynamicSqlSupport.deliveryAddressId
import com.example.orderprocessing.infrastructure.mapper.generated.OrdersBaseDynamicSqlSupport.deliveryCharge
import com.example.orderprocessing.infrastructure.mapper.generated.OrdersBaseDynamicSqlSupport.deliveryType
import com.example.orderprocessing.infrastructure.mapper.generated.OrdersBaseDynamicSqlSupport.nonTaxedTotalPrice
import com.example.orderprocessing.infrastructure.mapper.generated.OrdersBaseDynamicSqlSupport.orderId
import com.example.orderprocessing.infrastructure.mapper.generated.OrdersBaseDynamicSqlSupport.ordersBase
import com.example.orderprocessing.infrastructure.mapper.generated.OrdersBaseDynamicSqlSupport.paymentMethod
import com.example.orderprocessing.infrastructure.mapper.generated.OrdersBaseDynamicSqlSupport.shopId
import com.example.orderprocessing.infrastructure.mapper.generated.OrdersBaseDynamicSqlSupport.tax
import com.example.orderprocessing.infrastructure.mapper.generated.OrdersBaseDynamicSqlSupport.taxedTotalPrice
import com.example.orderprocessing.infrastructure.mapper.generated.OrdersBaseDynamicSqlSupport.time
import com.example.orderprocessing.infrastructure.mapper.generated.OrdersBaseDynamicSqlSupport.updatedAt
import com.example.orderprocessing.infrastructure.mapper.generated.OrdersBaseDynamicSqlSupport.userId
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
interface OrdersBaseMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<OrdersBase>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="OrdersBaseResult", value = [
        Result(column="order_id", property="orderId", jdbcType=JdbcType.VARCHAR, id=true),
        Result(column="chain_id", property="chainId", jdbcType=JdbcType.BIGINT),
        Result(column="shop_id", property="shopId", jdbcType=JdbcType.BIGINT),
        Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        Result(column="payment_method", property="paymentMethod", jdbcType=JdbcType.VARCHAR),
        Result(column="delivery_address_id", property="deliveryAddressId", jdbcType=JdbcType.BIGINT),
        Result(column="delivery_type", property="deliveryType", jdbcType=JdbcType.VARCHAR),
        Result(column="delivery_charge", property="deliveryCharge", jdbcType=JdbcType.DECIMAL),
        Result(column="non_taxed_total_price", property="nonTaxedTotalPrice", jdbcType=JdbcType.DECIMAL),
        Result(column="tax", property="tax", jdbcType=JdbcType.DECIMAL),
        Result(column="taxed_total_price", property="taxedTotalPrice", jdbcType=JdbcType.DECIMAL),
        Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        Result(column="black_level", property="blackLevel", jdbcType=JdbcType.INTEGER),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<OrdersBase>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("OrdersBaseResult")
    fun selectOne(selectStatement: SelectStatementProvider): OrdersBase?
}

fun OrdersBaseMapper.count(completer: CountCompleter) =
    countFrom(this::count, ordersBase, completer)

fun OrdersBaseMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, ordersBase, completer)

fun OrdersBaseMapper.deleteByPrimaryKey(orderId_: String) =
    delete {
        where { orderId isEqualTo orderId_ }
    }

fun OrdersBaseMapper.insert(row: OrdersBase) =
    insert(this::insert, row, ordersBase) {
        map(orderId) toProperty "orderId"
        map(chainId) toProperty "chainId"
        map(shopId) toProperty "shopId"
        map(userId) toProperty "userId"
        map(paymentMethod) toProperty "paymentMethod"
        map(deliveryAddressId) toProperty "deliveryAddressId"
        map(deliveryType) toProperty "deliveryType"
        map(deliveryCharge) toProperty "deliveryCharge"
        map(nonTaxedTotalPrice) toProperty "nonTaxedTotalPrice"
        map(tax) toProperty "tax"
        map(taxedTotalPrice) toProperty "taxedTotalPrice"
        map(time) toProperty "time"
        map(blackLevel) toProperty "blackLevel"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun OrdersBaseMapper.insertMultiple(records: Collection<OrdersBase>) =
    insertMultiple(this::insertMultiple, records, ordersBase) {
        map(orderId) toProperty "orderId"
        map(chainId) toProperty "chainId"
        map(shopId) toProperty "shopId"
        map(userId) toProperty "userId"
        map(paymentMethod) toProperty "paymentMethod"
        map(deliveryAddressId) toProperty "deliveryAddressId"
        map(deliveryType) toProperty "deliveryType"
        map(deliveryCharge) toProperty "deliveryCharge"
        map(nonTaxedTotalPrice) toProperty "nonTaxedTotalPrice"
        map(tax) toProperty "tax"
        map(taxedTotalPrice) toProperty "taxedTotalPrice"
        map(time) toProperty "time"
        map(blackLevel) toProperty "blackLevel"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun OrdersBaseMapper.insertMultiple(vararg records: OrdersBase) =
    insertMultiple(records.toList())

fun OrdersBaseMapper.insertSelective(row: OrdersBase) =
    insert(this::insert, row, ordersBase) {
        map(orderId).toPropertyWhenPresent("orderId", row::orderId)
        map(chainId).toPropertyWhenPresent("chainId", row::chainId)
        map(shopId).toPropertyWhenPresent("shopId", row::shopId)
        map(userId).toPropertyWhenPresent("userId", row::userId)
        map(paymentMethod).toPropertyWhenPresent("paymentMethod", row::paymentMethod)
        map(deliveryAddressId).toPropertyWhenPresent("deliveryAddressId", row::deliveryAddressId)
        map(deliveryType).toPropertyWhenPresent("deliveryType", row::deliveryType)
        map(deliveryCharge).toPropertyWhenPresent("deliveryCharge", row::deliveryCharge)
        map(nonTaxedTotalPrice).toPropertyWhenPresent("nonTaxedTotalPrice", row::nonTaxedTotalPrice)
        map(tax).toPropertyWhenPresent("tax", row::tax)
        map(taxedTotalPrice).toPropertyWhenPresent("taxedTotalPrice", row::taxedTotalPrice)
        map(time).toPropertyWhenPresent("time", row::time)
        map(blackLevel).toPropertyWhenPresent("blackLevel", row::blackLevel)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(orderId, chainId, shopId, userId, paymentMethod, deliveryAddressId, deliveryType, deliveryCharge, nonTaxedTotalPrice, tax, taxedTotalPrice, time, blackLevel, createdAt, updatedAt)

fun OrdersBaseMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, ordersBase, completer)

fun OrdersBaseMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, ordersBase, completer)

fun OrdersBaseMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, ordersBase, completer)

fun OrdersBaseMapper.selectByPrimaryKey(orderId_: String) =
    selectOne {
        where { orderId isEqualTo orderId_ }
    }

fun OrdersBaseMapper.update(completer: UpdateCompleter) =
    update(this::update, ordersBase, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: OrdersBase) =
    apply {
        set(orderId) equalToOrNull row::orderId
        set(chainId) equalToOrNull row::chainId
        set(shopId) equalToOrNull row::shopId
        set(userId) equalToOrNull row::userId
        set(paymentMethod) equalToOrNull row::paymentMethod
        set(deliveryAddressId) equalToOrNull row::deliveryAddressId
        set(deliveryType) equalToOrNull row::deliveryType
        set(deliveryCharge) equalToOrNull row::deliveryCharge
        set(nonTaxedTotalPrice) equalToOrNull row::nonTaxedTotalPrice
        set(tax) equalToOrNull row::tax
        set(taxedTotalPrice) equalToOrNull row::taxedTotalPrice
        set(time) equalToOrNull row::time
        set(blackLevel) equalToOrNull row::blackLevel
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: OrdersBase) =
    apply {
        set(orderId) equalToWhenPresent row::orderId
        set(chainId) equalToWhenPresent row::chainId
        set(shopId) equalToWhenPresent row::shopId
        set(userId) equalToWhenPresent row::userId
        set(paymentMethod) equalToWhenPresent row::paymentMethod
        set(deliveryAddressId) equalToWhenPresent row::deliveryAddressId
        set(deliveryType) equalToWhenPresent row::deliveryType
        set(deliveryCharge) equalToWhenPresent row::deliveryCharge
        set(nonTaxedTotalPrice) equalToWhenPresent row::nonTaxedTotalPrice
        set(tax) equalToWhenPresent row::tax
        set(taxedTotalPrice) equalToWhenPresent row::taxedTotalPrice
        set(time) equalToWhenPresent row::time
        set(blackLevel) equalToWhenPresent row::blackLevel
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun OrdersBaseMapper.updateByPrimaryKey(row: OrdersBase) =
    update {
        set(chainId) equalToOrNull row::chainId
        set(shopId) equalToOrNull row::shopId
        set(userId) equalToOrNull row::userId
        set(paymentMethod) equalToOrNull row::paymentMethod
        set(deliveryAddressId) equalToOrNull row::deliveryAddressId
        set(deliveryType) equalToOrNull row::deliveryType
        set(deliveryCharge) equalToOrNull row::deliveryCharge
        set(nonTaxedTotalPrice) equalToOrNull row::nonTaxedTotalPrice
        set(tax) equalToOrNull row::tax
        set(taxedTotalPrice) equalToOrNull row::taxedTotalPrice
        set(time) equalToOrNull row::time
        set(blackLevel) equalToOrNull row::blackLevel
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where { orderId isEqualTo row.orderId!! }
    }

fun OrdersBaseMapper.updateByPrimaryKeySelective(row: OrdersBase) =
    update {
        set(chainId) equalToWhenPresent row::chainId
        set(shopId) equalToWhenPresent row::shopId
        set(userId) equalToWhenPresent row::userId
        set(paymentMethod) equalToWhenPresent row::paymentMethod
        set(deliveryAddressId) equalToWhenPresent row::deliveryAddressId
        set(deliveryType) equalToWhenPresent row::deliveryType
        set(deliveryCharge) equalToWhenPresent row::deliveryCharge
        set(nonTaxedTotalPrice) equalToWhenPresent row::nonTaxedTotalPrice
        set(tax) equalToWhenPresent row::tax
        set(taxedTotalPrice) equalToWhenPresent row::taxedTotalPrice
        set(time) equalToWhenPresent row::time
        set(blackLevel) equalToWhenPresent row::blackLevel
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where { orderId isEqualTo row.orderId!! }
    }