/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.infrastructure.mapper.generated

import com.example.orderprocessing.infrastructure.entity.generated.ItemsBase
import com.example.orderprocessing.infrastructure.mapper.generated.ItemsBaseDynamicSqlSupport.chainId
import com.example.orderprocessing.infrastructure.mapper.generated.ItemsBaseDynamicSqlSupport.createdAt
import com.example.orderprocessing.infrastructure.mapper.generated.ItemsBaseDynamicSqlSupport.description
import com.example.orderprocessing.infrastructure.mapper.generated.ItemsBaseDynamicSqlSupport.itemId
import com.example.orderprocessing.infrastructure.mapper.generated.ItemsBaseDynamicSqlSupport.itemsBase
import com.example.orderprocessing.infrastructure.mapper.generated.ItemsBaseDynamicSqlSupport.name
import com.example.orderprocessing.infrastructure.mapper.generated.ItemsBaseDynamicSqlSupport.price
import com.example.orderprocessing.infrastructure.mapper.generated.ItemsBaseDynamicSqlSupport.shopId
import com.example.orderprocessing.infrastructure.mapper.generated.ItemsBaseDynamicSqlSupport.updatedAt
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
interface ItemsBaseMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<ItemsBase>, CommonUpdateMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "ItemsBaseResult", value = [
            Result(column = "item_id", property = "itemId", jdbcType = JdbcType.BIGINT, id = true),
            Result(column = "chain_id", property = "chainId", jdbcType = JdbcType.BIGINT),
            Result(column = "shop_id", property = "shopId", jdbcType = JdbcType.BIGINT),
            Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL),
            Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP)
        ]
    )
    fun selectMany(selectStatement: SelectStatementProvider): List<ItemsBase>

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @ResultMap("ItemsBaseResult")
    fun selectOne(selectStatement: SelectStatementProvider): ItemsBase?
}

fun ItemsBaseMapper.count(completer: CountCompleter) =
    countFrom(this::count, itemsBase, completer)

fun ItemsBaseMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, itemsBase, completer)

fun ItemsBaseMapper.deleteByPrimaryKey(itemId_: Long) =
    delete {
        where { itemId isEqualTo itemId_ }
    }

fun ItemsBaseMapper.insert(row: ItemsBase) =
    insert(this::insert, row, itemsBase) {
        map(itemId) toProperty "itemId"
        map(chainId) toProperty "chainId"
        map(shopId) toProperty "shopId"
        map(name) toProperty "name"
        map(price) toProperty "price"
        map(description) toProperty "description"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun ItemsBaseMapper.insertMultiple(records: Collection<ItemsBase>) =
    insertMultiple(this::insertMultiple, records, itemsBase) {
        map(itemId) toProperty "itemId"
        map(chainId) toProperty "chainId"
        map(shopId) toProperty "shopId"
        map(name) toProperty "name"
        map(price) toProperty "price"
        map(description) toProperty "description"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun ItemsBaseMapper.insertMultiple(vararg records: ItemsBase) =
    insertMultiple(records.toList())

fun ItemsBaseMapper.insertSelective(row: ItemsBase) =
    insert(this::insert, row, itemsBase) {
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

fun ItemsBaseMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, itemsBase, completer)

fun ItemsBaseMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, itemsBase, completer)

fun ItemsBaseMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, itemsBase, completer)

fun ItemsBaseMapper.selectByPrimaryKey(itemId_: Long) =
    selectOne {
        where { itemId isEqualTo itemId_ }
    }

fun ItemsBaseMapper.update(completer: UpdateCompleter) =
    update(this::update, itemsBase, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: ItemsBase) =
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

fun KotlinUpdateBuilder.updateSelectiveColumns(row: ItemsBase) =
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

fun ItemsBaseMapper.updateByPrimaryKey(row: ItemsBase) =
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

fun ItemsBaseMapper.updateByPrimaryKeySelective(row: ItemsBase) =
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