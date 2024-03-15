/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderprocessing.repository.mapper.generated

import com.example.orderprocessing.repository.entity.generated.PaymentMethodsBase
import com.example.orderprocessing.repository.mapper.generated.PaymentMethodsBaseDynamicSqlSupport.createdAt
import com.example.orderprocessing.repository.mapper.generated.PaymentMethodsBaseDynamicSqlSupport.name
import com.example.orderprocessing.repository.mapper.generated.PaymentMethodsBaseDynamicSqlSupport.paymentMethodsBase
import com.example.orderprocessing.repository.mapper.generated.PaymentMethodsBaseDynamicSqlSupport.updatedAt
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
interface PaymentMethodsBaseMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<PaymentMethodsBase>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="PaymentMethodsBaseResult", value = [
        Result(column="name", property="name", jdbcType=JdbcType.VARCHAR, id=true),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<PaymentMethodsBase>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("PaymentMethodsBaseResult")
    fun selectOne(selectStatement: SelectStatementProvider): PaymentMethodsBase?
}

fun PaymentMethodsBaseMapper.count(completer: CountCompleter) =
    countFrom(this::count, paymentMethodsBase, completer)

fun PaymentMethodsBaseMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, paymentMethodsBase, completer)

fun PaymentMethodsBaseMapper.deleteByPrimaryKey(name_: String) =
    delete {
        where { name isEqualTo name_ }
    }

fun PaymentMethodsBaseMapper.insert(row: PaymentMethodsBase) =
    insert(this::insert, row, paymentMethodsBase) {
        map(name) toProperty "name"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun PaymentMethodsBaseMapper.insertMultiple(records: Collection<PaymentMethodsBase>) =
    insertMultiple(this::insertMultiple, records, paymentMethodsBase) {
        map(name) toProperty "name"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun PaymentMethodsBaseMapper.insertMultiple(vararg records: PaymentMethodsBase) =
    insertMultiple(records.toList())

fun PaymentMethodsBaseMapper.insertSelective(row: PaymentMethodsBase) =
    insert(this::insert, row, paymentMethodsBase) {
        map(name).toPropertyWhenPresent("name", row::name)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(name, createdAt, updatedAt)

fun PaymentMethodsBaseMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, paymentMethodsBase, completer)

fun PaymentMethodsBaseMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, paymentMethodsBase, completer)

fun PaymentMethodsBaseMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, paymentMethodsBase, completer)

fun PaymentMethodsBaseMapper.selectByPrimaryKey(name_: String) =
    selectOne {
        where { name isEqualTo name_ }
    }

fun PaymentMethodsBaseMapper.update(completer: UpdateCompleter) =
    update(this::update, paymentMethodsBase, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: PaymentMethodsBase) =
    apply {
        set(name) equalToOrNull row::name
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: PaymentMethodsBase) =
    apply {
        set(name) equalToWhenPresent row::name
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun PaymentMethodsBaseMapper.updateByPrimaryKey(row: PaymentMethodsBase) =
    update {
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where { name isEqualTo row.name!! }
    }

fun PaymentMethodsBaseMapper.updateByPrimaryKeySelective(row: PaymentMethodsBase) =
    update {
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where { name isEqualTo row.name!! }
    }