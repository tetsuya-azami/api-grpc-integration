/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderreception.infrastructure.mapper.generated

import com.example.orderreception.infrastructure.entity.generated.AddressesBase
import com.example.orderreception.infrastructure.mapper.generated.AddressesBaseDynamicSqlSupport.addressId
import com.example.orderreception.infrastructure.mapper.generated.AddressesBaseDynamicSqlSupport.addressesBase
import com.example.orderreception.infrastructure.mapper.generated.AddressesBaseDynamicSqlSupport.building
import com.example.orderreception.infrastructure.mapper.generated.AddressesBaseDynamicSqlSupport.city
import com.example.orderreception.infrastructure.mapper.generated.AddressesBaseDynamicSqlSupport.createdAt
import com.example.orderreception.infrastructure.mapper.generated.AddressesBaseDynamicSqlSupport.postcode
import com.example.orderreception.infrastructure.mapper.generated.AddressesBaseDynamicSqlSupport.prefecture
import com.example.orderreception.infrastructure.mapper.generated.AddressesBaseDynamicSqlSupport.streetAddress
import com.example.orderreception.infrastructure.mapper.generated.AddressesBaseDynamicSqlSupport.updatedAt
import com.example.orderreception.infrastructure.mapper.generated.AddressesBaseDynamicSqlSupport.userId
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
interface AddressesBaseMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<AddressesBase>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="AddressesBaseResult", value = [
        Result(column="address_id", property="addressId", jdbcType=JdbcType.BIGINT, id=true),
        Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        Result(column="postcode", property="postcode", jdbcType=JdbcType.VARCHAR),
        Result(column="prefecture", property="prefecture", jdbcType=JdbcType.VARCHAR),
        Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        Result(column="street_address", property="streetAddress", jdbcType=JdbcType.VARCHAR),
        Result(column="building", property="building", jdbcType=JdbcType.VARCHAR),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<AddressesBase>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("AddressesBaseResult")
    fun selectOne(selectStatement: SelectStatementProvider): AddressesBase?
}

fun AddressesBaseMapper.count(completer: CountCompleter) =
    countFrom(this::count, addressesBase, completer)

fun AddressesBaseMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, addressesBase, completer)

fun AddressesBaseMapper.deleteByPrimaryKey(addressId_: Long) =
    delete {
        where { addressId isEqualTo addressId_ }
    }

fun AddressesBaseMapper.insert(row: AddressesBase) =
    insert(this::insert, row, addressesBase) {
        map(addressId) toProperty "addressId"
        map(userId) toProperty "userId"
        map(postcode) toProperty "postcode"
        map(prefecture) toProperty "prefecture"
        map(city) toProperty "city"
        map(streetAddress) toProperty "streetAddress"
        map(building) toProperty "building"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun AddressesBaseMapper.insertMultiple(records: Collection<AddressesBase>) =
    insertMultiple(this::insertMultiple, records, addressesBase) {
        map(addressId) toProperty "addressId"
        map(userId) toProperty "userId"
        map(postcode) toProperty "postcode"
        map(prefecture) toProperty "prefecture"
        map(city) toProperty "city"
        map(streetAddress) toProperty "streetAddress"
        map(building) toProperty "building"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun AddressesBaseMapper.insertMultiple(vararg records: AddressesBase) =
    insertMultiple(records.toList())

fun AddressesBaseMapper.insertSelective(row: AddressesBase) =
    insert(this::insert, row, addressesBase) {
        map(addressId).toPropertyWhenPresent("addressId", row::addressId)
        map(userId).toPropertyWhenPresent("userId", row::userId)
        map(postcode).toPropertyWhenPresent("postcode", row::postcode)
        map(prefecture).toPropertyWhenPresent("prefecture", row::prefecture)
        map(city).toPropertyWhenPresent("city", row::city)
        map(streetAddress).toPropertyWhenPresent("streetAddress", row::streetAddress)
        map(building).toPropertyWhenPresent("building", row::building)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(addressId, userId, postcode, prefecture, city, streetAddress, building, createdAt, updatedAt)

fun AddressesBaseMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, addressesBase, completer)

fun AddressesBaseMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, addressesBase, completer)

fun AddressesBaseMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, addressesBase, completer)

fun AddressesBaseMapper.selectByPrimaryKey(addressId_: Long) =
    selectOne {
        where { addressId isEqualTo addressId_ }
    }

fun AddressesBaseMapper.update(completer: UpdateCompleter) =
    update(this::update, addressesBase, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: AddressesBase) =
    apply {
        set(addressId) equalToOrNull row::addressId
        set(userId) equalToOrNull row::userId
        set(postcode) equalToOrNull row::postcode
        set(prefecture) equalToOrNull row::prefecture
        set(city) equalToOrNull row::city
        set(streetAddress) equalToOrNull row::streetAddress
        set(building) equalToOrNull row::building
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: AddressesBase) =
    apply {
        set(addressId) equalToWhenPresent row::addressId
        set(userId) equalToWhenPresent row::userId
        set(postcode) equalToWhenPresent row::postcode
        set(prefecture) equalToWhenPresent row::prefecture
        set(city) equalToWhenPresent row::city
        set(streetAddress) equalToWhenPresent row::streetAddress
        set(building) equalToWhenPresent row::building
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun AddressesBaseMapper.updateByPrimaryKey(row: AddressesBase) =
    update {
        set(userId) equalToOrNull row::userId
        set(postcode) equalToOrNull row::postcode
        set(prefecture) equalToOrNull row::prefecture
        set(city) equalToOrNull row::city
        set(streetAddress) equalToOrNull row::streetAddress
        set(building) equalToOrNull row::building
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where { addressId isEqualTo row.addressId!! }
    }

fun AddressesBaseMapper.updateByPrimaryKeySelective(row: AddressesBase) =
    update {
        set(userId) equalToWhenPresent row::userId
        set(postcode) equalToWhenPresent row::postcode
        set(prefecture) equalToWhenPresent row::prefecture
        set(city) equalToWhenPresent row::city
        set(streetAddress) equalToWhenPresent row::streetAddress
        set(building) equalToWhenPresent row::building
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where { addressId isEqualTo row.addressId!! }
    }