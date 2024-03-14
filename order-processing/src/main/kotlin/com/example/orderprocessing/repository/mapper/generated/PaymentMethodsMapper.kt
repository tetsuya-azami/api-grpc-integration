/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.repository.mapper.generated

import com.example.orderprocessing.repository.entity.generated.PaymentMethods
import com.example.orderprocessing.repository.mapper.generated.PaymentMethodsDynamicSqlSupport.createdAt
import com.example.orderprocessing.repository.mapper.generated.PaymentMethodsDynamicSqlSupport.name
import com.example.orderprocessing.repository.mapper.generated.PaymentMethodsDynamicSqlSupport.paymentMethods
import com.example.orderprocessing.repository.mapper.generated.PaymentMethodsDynamicSqlSupport.updatedAt
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
interface PaymentMethodsMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<PaymentMethods>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="PaymentMethodsResult", value = [
        Result(column="name", property="name", jdbcType=JdbcType.VARCHAR, id=true),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<PaymentMethods>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("PaymentMethodsResult")
    fun selectOne(selectStatement: SelectStatementProvider): PaymentMethods?
}

fun PaymentMethodsMapper.count(completer: CountCompleter) =
    countFrom(this::count, paymentMethods, completer)

fun PaymentMethodsMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, paymentMethods, completer)

fun PaymentMethodsMapper.deleteByPrimaryKey(name_: String) =
    delete {
        where { name isEqualTo name_ }
    }

fun PaymentMethodsMapper.insert(row: PaymentMethods) =
    insert(this::insert, row, paymentMethods) {
        map(name) toProperty "name"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun PaymentMethodsMapper.insertMultiple(records: Collection<PaymentMethods>) =
    insertMultiple(this::insertMultiple, records, paymentMethods) {
        map(name) toProperty "name"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun PaymentMethodsMapper.insertMultiple(vararg records: PaymentMethods) =
    insertMultiple(records.toList())

fun PaymentMethodsMapper.insertSelective(row: PaymentMethods) =
    insert(this::insert, row, paymentMethods) {
        map(name).toPropertyWhenPresent("name", row::name)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(name, createdAt, updatedAt)

fun PaymentMethodsMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, paymentMethods, completer)

fun PaymentMethodsMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, paymentMethods, completer)

fun PaymentMethodsMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, paymentMethods, completer)

fun PaymentMethodsMapper.selectByPrimaryKey(name_: String) =
    selectOne {
        where { name isEqualTo name_ }
    }

fun PaymentMethodsMapper.update(completer: UpdateCompleter) =
    update(this::update, paymentMethods, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: PaymentMethods) =
    apply {
        set(name) equalToOrNull row::name
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: PaymentMethods) =
    apply {
        set(name) equalToWhenPresent row::name
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun PaymentMethodsMapper.updateByPrimaryKey(row: PaymentMethods) =
    update {
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where { name isEqualTo row.name!! }
    }

fun PaymentMethodsMapper.updateByPrimaryKeySelective(row: PaymentMethods) =
    update {
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where { name isEqualTo row.name!! }
    }