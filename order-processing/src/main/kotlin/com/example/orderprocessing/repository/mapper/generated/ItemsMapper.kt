/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-03-11T09:23:44.886827+09:00
 */
package com.example.orderprocessing.repository.mapper.generated

import com.example.orderprocessing.repository.entity.generated.Items
import com.example.orderprocessing.repository.mapper.generated.ItemsDynamicSqlSupport.chainId
import com.example.orderprocessing.repository.mapper.generated.ItemsDynamicSqlSupport.createdAt
import com.example.orderprocessing.repository.mapper.generated.ItemsDynamicSqlSupport.description
import com.example.orderprocessing.repository.mapper.generated.ItemsDynamicSqlSupport.itemId
import com.example.orderprocessing.repository.mapper.generated.ItemsDynamicSqlSupport.items
import com.example.orderprocessing.repository.mapper.generated.ItemsDynamicSqlSupport.name
import com.example.orderprocessing.repository.mapper.generated.ItemsDynamicSqlSupport.price
import com.example.orderprocessing.repository.mapper.generated.ItemsDynamicSqlSupport.shopId
import com.example.orderprocessing.repository.mapper.generated.ItemsDynamicSqlSupport.updatedAt
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
interface ItemsMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Items>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="ItemsResult", value = [
        Result(column="item_id", property="itemId", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="chain_id", property="chainId", jdbcType=JdbcType.INTEGER),
        Result(column="shop_id", property="shopId", jdbcType=JdbcType.INTEGER),
        Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<Items>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("ItemsResult")
    fun selectOne(selectStatement: SelectStatementProvider): Items?
}

fun ItemsMapper.count(completer: CountCompleter) =
    countFrom(this::count, items, completer)

fun ItemsMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, items, completer)

fun ItemsMapper.deleteByPrimaryKey(itemId_: Int) =
    delete {
        where { itemId isEqualTo itemId_ }
    }

fun ItemsMapper.insert(row: Items) =
    insert(this::insert, row, items) {
        map(itemId) toProperty "itemId"
        map(chainId) toProperty "chainId"
        map(shopId) toProperty "shopId"
        map(name) toProperty "name"
        map(price) toProperty "price"
        map(description) toProperty "description"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun ItemsMapper.insertMultiple(records: Collection<Items>) =
    insertMultiple(this::insertMultiple, records, items) {
        map(itemId) toProperty "itemId"
        map(chainId) toProperty "chainId"
        map(shopId) toProperty "shopId"
        map(name) toProperty "name"
        map(price) toProperty "price"
        map(description) toProperty "description"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun ItemsMapper.insertMultiple(vararg records: Items) =
    insertMultiple(records.toList())

fun ItemsMapper.insertSelective(row: Items) =
    insert(this::insert, row, items) {
        map(itemId).toPropertyWhenPresent("itemId", row::itemId)
        map(chainId).toPropertyWhenPresent("chainId", row::chainId)
        map(shopId).toPropertyWhenPresent("shopId", row::shopId)
        map(name).toPropertyWhenPresent("name", row::name)
        map(price).toPropertyWhenPresent("price", row::price)
        map(description).toPropertyWhenPresent("description", row::description)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(itemId, chainId, shopId, name, price, description, createdAt, updatedAt)

fun ItemsMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, items, completer)

fun ItemsMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, items, completer)

fun ItemsMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, items, completer)

fun ItemsMapper.selectByPrimaryKey(itemId_: Int) =
    selectOne {
        where { itemId isEqualTo itemId_ }
    }

fun ItemsMapper.update(completer: UpdateCompleter) =
    update(this::update, items, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: Items) =
    apply {
        set(itemId) equalToOrNull row::itemId
        set(chainId) equalToOrNull row::chainId
        set(shopId) equalToOrNull row::shopId
        set(name) equalToOrNull row::name
        set(price) equalToOrNull row::price
        set(description) equalToOrNull row::description
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: Items) =
    apply {
        set(itemId) equalToWhenPresent row::itemId
        set(chainId) equalToWhenPresent row::chainId
        set(shopId) equalToWhenPresent row::shopId
        set(name) equalToWhenPresent row::name
        set(price) equalToWhenPresent row::price
        set(description) equalToWhenPresent row::description
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun ItemsMapper.updateByPrimaryKey(row: Items) =
    update {
        set(chainId) equalToOrNull row::chainId
        set(shopId) equalToOrNull row::shopId
        set(name) equalToOrNull row::name
        set(price) equalToOrNull row::price
        set(description) equalToOrNull row::description
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where { itemId isEqualTo row.itemId!! }
    }

fun ItemsMapper.updateByPrimaryKeySelective(row: Items) =
    update {
        set(chainId) equalToWhenPresent row::chainId
        set(shopId) equalToWhenPresent row::shopId
        set(name) equalToWhenPresent row::name
        set(price) equalToWhenPresent row::price
        set(description) equalToWhenPresent row::description
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where { itemId isEqualTo row.itemId!! }
    }