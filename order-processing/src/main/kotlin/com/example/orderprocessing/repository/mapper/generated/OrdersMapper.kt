/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-03-11T09:23:44.881149+09:00
 */
package com.example.orderprocessing.repository.mapper.generated

import com.example.orderprocessing.repository.entity.generated.Orders
import com.example.orderprocessing.repository.mapper.generated.OrdersDynamicSqlSupport.chainId
import com.example.orderprocessing.repository.mapper.generated.OrdersDynamicSqlSupport.createdAt
import com.example.orderprocessing.repository.mapper.generated.OrdersDynamicSqlSupport.deliveryAddressId
import com.example.orderprocessing.repository.mapper.generated.OrdersDynamicSqlSupport.deliveryCharge
import com.example.orderprocessing.repository.mapper.generated.OrdersDynamicSqlSupport.deliveryMethodId
import com.example.orderprocessing.repository.mapper.generated.OrdersDynamicSqlSupport.deliveryType
import com.example.orderprocessing.repository.mapper.generated.OrdersDynamicSqlSupport.nonTaxedTotalPrice
import com.example.orderprocessing.repository.mapper.generated.OrdersDynamicSqlSupport.orderId
import com.example.orderprocessing.repository.mapper.generated.OrdersDynamicSqlSupport.orders
import com.example.orderprocessing.repository.mapper.generated.OrdersDynamicSqlSupport.paymentMethod
import com.example.orderprocessing.repository.mapper.generated.OrdersDynamicSqlSupport.shopId
import com.example.orderprocessing.repository.mapper.generated.OrdersDynamicSqlSupport.tax
import com.example.orderprocessing.repository.mapper.generated.OrdersDynamicSqlSupport.taxedTotalPrice
import com.example.orderprocessing.repository.mapper.generated.OrdersDynamicSqlSupport.time
import com.example.orderprocessing.repository.mapper.generated.OrdersDynamicSqlSupport.updatedAt
import com.example.orderprocessing.repository.mapper.generated.OrdersDynamicSqlSupport.userId
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
interface OrdersMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Orders>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="OrdersResult", value = [
        Result(column="order_id", property="orderId", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="chain_id", property="chainId", jdbcType=JdbcType.INTEGER),
        Result(column="shop_id", property="shopId", jdbcType=JdbcType.INTEGER),
        Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        Result(column="payment_method", property="paymentMethod", jdbcType=JdbcType.VARCHAR),
        Result(column="delivery_address_id", property="deliveryAddressId", jdbcType=JdbcType.INTEGER),
        Result(column="delivery_type", property="deliveryType", jdbcType=JdbcType.VARCHAR),
        Result(column="delivery_method_id", property="deliveryMethodId", jdbcType=JdbcType.INTEGER),
        Result(column="delivery_charge", property="deliveryCharge", jdbcType=JdbcType.DECIMAL),
        Result(column="non_taxed_total_price", property="nonTaxedTotalPrice", jdbcType=JdbcType.DECIMAL),
        Result(column="tax", property="tax", jdbcType=JdbcType.DECIMAL),
        Result(column="taxed_total_price", property="taxedTotalPrice", jdbcType=JdbcType.DECIMAL),
        Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<Orders>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("OrdersResult")
    fun selectOne(selectStatement: SelectStatementProvider): Orders?
}

fun OrdersMapper.count(completer: CountCompleter) =
    countFrom(this::count, orders, completer)

fun OrdersMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, orders, completer)

fun OrdersMapper.deleteByPrimaryKey(orderId_: Int) =
    delete {
        where { orderId isEqualTo orderId_ }
    }

fun OrdersMapper.insert(row: Orders) =
    insert(this::insert, row, orders) {
        map(orderId) toProperty "orderId"
        map(chainId) toProperty "chainId"
        map(shopId) toProperty "shopId"
        map(userId) toProperty "userId"
        map(paymentMethod) toProperty "paymentMethod"
        map(deliveryAddressId) toProperty "deliveryAddressId"
        map(deliveryType) toProperty "deliveryType"
        map(deliveryMethodId) toProperty "deliveryMethodId"
        map(deliveryCharge) toProperty "deliveryCharge"
        map(nonTaxedTotalPrice) toProperty "nonTaxedTotalPrice"
        map(tax) toProperty "tax"
        map(taxedTotalPrice) toProperty "taxedTotalPrice"
        map(time) toProperty "time"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun OrdersMapper.insertMultiple(records: Collection<Orders>) =
    insertMultiple(this::insertMultiple, records, orders) {
        map(orderId) toProperty "orderId"
        map(chainId) toProperty "chainId"
        map(shopId) toProperty "shopId"
        map(userId) toProperty "userId"
        map(paymentMethod) toProperty "paymentMethod"
        map(deliveryAddressId) toProperty "deliveryAddressId"
        map(deliveryType) toProperty "deliveryType"
        map(deliveryMethodId) toProperty "deliveryMethodId"
        map(deliveryCharge) toProperty "deliveryCharge"
        map(nonTaxedTotalPrice) toProperty "nonTaxedTotalPrice"
        map(tax) toProperty "tax"
        map(taxedTotalPrice) toProperty "taxedTotalPrice"
        map(time) toProperty "time"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun OrdersMapper.insertMultiple(vararg records: Orders) =
    insertMultiple(records.toList())

fun OrdersMapper.insertSelective(row: Orders) =
    insert(this::insert, row, orders) {
        map(orderId).toPropertyWhenPresent("orderId", row::orderId)
        map(chainId).toPropertyWhenPresent("chainId", row::chainId)
        map(shopId).toPropertyWhenPresent("shopId", row::shopId)
        map(userId).toPropertyWhenPresent("userId", row::userId)
        map(paymentMethod).toPropertyWhenPresent("paymentMethod", row::paymentMethod)
        map(deliveryAddressId).toPropertyWhenPresent("deliveryAddressId", row::deliveryAddressId)
        map(deliveryType).toPropertyWhenPresent("deliveryType", row::deliveryType)
        map(deliveryMethodId).toPropertyWhenPresent("deliveryMethodId", row::deliveryMethodId)
        map(deliveryCharge).toPropertyWhenPresent("deliveryCharge", row::deliveryCharge)
        map(nonTaxedTotalPrice).toPropertyWhenPresent("nonTaxedTotalPrice", row::nonTaxedTotalPrice)
        map(tax).toPropertyWhenPresent("tax", row::tax)
        map(taxedTotalPrice).toPropertyWhenPresent("taxedTotalPrice", row::taxedTotalPrice)
        map(time).toPropertyWhenPresent("time", row::time)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(orderId, chainId, shopId, userId, paymentMethod, deliveryAddressId, deliveryType, deliveryMethodId, deliveryCharge, nonTaxedTotalPrice, tax, taxedTotalPrice, time, createdAt, updatedAt)

fun OrdersMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, orders, completer)

fun OrdersMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, orders, completer)

fun OrdersMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, orders, completer)

fun OrdersMapper.selectByPrimaryKey(orderId_: Int) =
    selectOne {
        where { orderId isEqualTo orderId_ }
    }

fun OrdersMapper.update(completer: UpdateCompleter) =
    update(this::update, orders, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: Orders) =
    apply {
        set(orderId) equalToOrNull row::orderId
        set(chainId) equalToOrNull row::chainId
        set(shopId) equalToOrNull row::shopId
        set(userId) equalToOrNull row::userId
        set(paymentMethod) equalToOrNull row::paymentMethod
        set(deliveryAddressId) equalToOrNull row::deliveryAddressId
        set(deliveryType) equalToOrNull row::deliveryType
        set(deliveryMethodId) equalToOrNull row::deliveryMethodId
        set(deliveryCharge) equalToOrNull row::deliveryCharge
        set(nonTaxedTotalPrice) equalToOrNull row::nonTaxedTotalPrice
        set(tax) equalToOrNull row::tax
        set(taxedTotalPrice) equalToOrNull row::taxedTotalPrice
        set(time) equalToOrNull row::time
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: Orders) =
    apply {
        set(orderId) equalToWhenPresent row::orderId
        set(chainId) equalToWhenPresent row::chainId
        set(shopId) equalToWhenPresent row::shopId
        set(userId) equalToWhenPresent row::userId
        set(paymentMethod) equalToWhenPresent row::paymentMethod
        set(deliveryAddressId) equalToWhenPresent row::deliveryAddressId
        set(deliveryType) equalToWhenPresent row::deliveryType
        set(deliveryMethodId) equalToWhenPresent row::deliveryMethodId
        set(deliveryCharge) equalToWhenPresent row::deliveryCharge
        set(nonTaxedTotalPrice) equalToWhenPresent row::nonTaxedTotalPrice
        set(tax) equalToWhenPresent row::tax
        set(taxedTotalPrice) equalToWhenPresent row::taxedTotalPrice
        set(time) equalToWhenPresent row::time
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun OrdersMapper.updateByPrimaryKey(row: Orders) =
    update {
        set(chainId) equalToOrNull row::chainId
        set(shopId) equalToOrNull row::shopId
        set(userId) equalToOrNull row::userId
        set(paymentMethod) equalToOrNull row::paymentMethod
        set(deliveryAddressId) equalToOrNull row::deliveryAddressId
        set(deliveryType) equalToOrNull row::deliveryType
        set(deliveryMethodId) equalToOrNull row::deliveryMethodId
        set(deliveryCharge) equalToOrNull row::deliveryCharge
        set(nonTaxedTotalPrice) equalToOrNull row::nonTaxedTotalPrice
        set(tax) equalToOrNull row::tax
        set(taxedTotalPrice) equalToOrNull row::taxedTotalPrice
        set(time) equalToOrNull row::time
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where { orderId isEqualTo row.orderId!! }
    }

fun OrdersMapper.updateByPrimaryKeySelective(row: Orders) =
    update {
        set(chainId) equalToWhenPresent row::chainId
        set(shopId) equalToWhenPresent row::shopId
        set(userId) equalToWhenPresent row::userId
        set(paymentMethod) equalToWhenPresent row::paymentMethod
        set(deliveryAddressId) equalToWhenPresent row::deliveryAddressId
        set(deliveryType) equalToWhenPresent row::deliveryType
        set(deliveryMethodId) equalToWhenPresent row::deliveryMethodId
        set(deliveryCharge) equalToWhenPresent row::deliveryCharge
        set(nonTaxedTotalPrice) equalToWhenPresent row::nonTaxedTotalPrice
        set(tax) equalToWhenPresent row::tax
        set(taxedTotalPrice) equalToWhenPresent row::taxedTotalPrice
        set(time) equalToWhenPresent row::time
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where { orderId isEqualTo row.orderId!! }
    }