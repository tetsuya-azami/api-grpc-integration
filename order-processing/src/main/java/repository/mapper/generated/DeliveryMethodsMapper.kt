/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-03-11T09:10:07.208333+09:00
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
import repository.entity.generated.DeliveryMethods
import repository.mapper.generated.DeliveryMethodsDynamicSqlSupport.createdAt
import repository.mapper.generated.DeliveryMethodsDynamicSqlSupport.deliveryMethods
import repository.mapper.generated.DeliveryMethodsDynamicSqlSupport.name
import repository.mapper.generated.DeliveryMethodsDynamicSqlSupport.updatedAt

@Mapper
interface DeliveryMethodsMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<DeliveryMethods>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="DeliveryMethodsResult", value = [
        Result(column="name", property="name", jdbcType=JdbcType.VARCHAR, id=true),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<DeliveryMethods>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("DeliveryMethodsResult")
    fun selectOne(selectStatement: SelectStatementProvider): DeliveryMethods?
}

fun DeliveryMethodsMapper.count(completer: CountCompleter) =
    countFrom(this::count, deliveryMethods, completer)

fun DeliveryMethodsMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, deliveryMethods, completer)

fun DeliveryMethodsMapper.deleteByPrimaryKey(name_: String) =
    delete {
        where { name isEqualTo name_ }
    }

fun DeliveryMethodsMapper.insert(row: DeliveryMethods) =
    insert(this::insert, row, deliveryMethods) {
        map(name) toProperty "name"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun DeliveryMethodsMapper.insertMultiple(records: Collection<DeliveryMethods>) =
    insertMultiple(this::insertMultiple, records, deliveryMethods) {
        map(name) toProperty "name"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun DeliveryMethodsMapper.insertMultiple(vararg records: DeliveryMethods) =
    insertMultiple(records.toList())

fun DeliveryMethodsMapper.insertSelective(row: DeliveryMethods) =
    insert(this::insert, row, deliveryMethods) {
        map(name).toPropertyWhenPresent("name", row::name)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(name, createdAt, updatedAt)

fun DeliveryMethodsMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, deliveryMethods, completer)

fun DeliveryMethodsMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, deliveryMethods, completer)

fun DeliveryMethodsMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, deliveryMethods, completer)

fun DeliveryMethodsMapper.selectByPrimaryKey(name_: String) =
    selectOne {
        where { name isEqualTo name_ }
    }

fun DeliveryMethodsMapper.update(completer: UpdateCompleter) =
    update(this::update, deliveryMethods, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: DeliveryMethods) =
    apply {
        set(name) equalToOrNull row::name
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: DeliveryMethods) =
    apply {
        set(name) equalToWhenPresent row::name
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun DeliveryMethodsMapper.updateByPrimaryKey(row: DeliveryMethods) =
    update {
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where { name isEqualTo row.name!! }
    }

fun DeliveryMethodsMapper.updateByPrimaryKeySelective(row: DeliveryMethods) =
    update {
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where { name isEqualTo row.name!! }
    }