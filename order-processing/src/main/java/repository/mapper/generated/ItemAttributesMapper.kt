/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-03-11T09:10:07.205816+09:00
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
import repository.entity.generated.ItemAttributes
import repository.mapper.generated.ItemAttributesDynamicSqlSupport.attributeId
import repository.mapper.generated.ItemAttributesDynamicSqlSupport.createdAt
import repository.mapper.generated.ItemAttributesDynamicSqlSupport.itemAttributes
import repository.mapper.generated.ItemAttributesDynamicSqlSupport.itemId
import repository.mapper.generated.ItemAttributesDynamicSqlSupport.updatedAt

@Mapper
interface ItemAttributesMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<ItemAttributes>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="ItemAttributesResult", value = [
        Result(column="item_id", property="itemId", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="attribute_id", property="attributeId", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<ItemAttributes>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("ItemAttributesResult")
    fun selectOne(selectStatement: SelectStatementProvider): ItemAttributes?
}

fun ItemAttributesMapper.count(completer: CountCompleter) =
    countFrom(this::count, itemAttributes, completer)

fun ItemAttributesMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, itemAttributes, completer)

fun ItemAttributesMapper.deleteByPrimaryKey(itemId_: Int, attributeId_: Int) =
    delete {
        where {
            itemId isEqualTo itemId_
            and { attributeId isEqualTo attributeId_ }
        }
    }

fun ItemAttributesMapper.insert(row: ItemAttributes) =
    insert(this::insert, row, itemAttributes) {
        map(itemId) toProperty "itemId"
        map(attributeId) toProperty "attributeId"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun ItemAttributesMapper.insertMultiple(records: Collection<ItemAttributes>) =
    insertMultiple(this::insertMultiple, records, itemAttributes) {
        map(itemId) toProperty "itemId"
        map(attributeId) toProperty "attributeId"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun ItemAttributesMapper.insertMultiple(vararg records: ItemAttributes) =
    insertMultiple(records.toList())

fun ItemAttributesMapper.insertSelective(row: ItemAttributes) =
    insert(this::insert, row, itemAttributes) {
        map(itemId).toPropertyWhenPresent("itemId", row::itemId)
        map(attributeId).toPropertyWhenPresent("attributeId", row::attributeId)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(itemId, attributeId, createdAt, updatedAt)

fun ItemAttributesMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, itemAttributes, completer)

fun ItemAttributesMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, itemAttributes, completer)

fun ItemAttributesMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, itemAttributes, completer)

fun ItemAttributesMapper.selectByPrimaryKey(itemId_: Int, attributeId_: Int) =
    selectOne {
        where {
            itemId isEqualTo itemId_
            and { attributeId isEqualTo attributeId_ }
        }
    }

fun ItemAttributesMapper.update(completer: UpdateCompleter) =
    update(this::update, itemAttributes, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: ItemAttributes) =
    apply {
        set(itemId) equalToOrNull row::itemId
        set(attributeId) equalToOrNull row::attributeId
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: ItemAttributes) =
    apply {
        set(itemId) equalToWhenPresent row::itemId
        set(attributeId) equalToWhenPresent row::attributeId
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun ItemAttributesMapper.updateByPrimaryKey(row: ItemAttributes) =
    update {
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where {
            itemId isEqualTo row.itemId!!
            and { attributeId isEqualTo row.attributeId!! }
        }
    }

fun ItemAttributesMapper.updateByPrimaryKeySelective(row: ItemAttributes) =
    update {
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where {
            itemId isEqualTo row.itemId!!
            and { attributeId isEqualTo row.attributeId!! }
        }
    }