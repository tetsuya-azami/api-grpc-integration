package com.example.orderreception.infrastructure.mapper.custom

import com.example.orderreception.infrastructure.entity.custom.ItemWithAttributesBase
import com.example.orderreception.infrastructure.entity.generated.AttributesBase
import org.apache.ibatis.annotations.*
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter

@Mapper
interface ItemAttributesMappler {

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "ItemAttributesBaseResult", value = [
            Result(column = "item_id", property = "itemId", jdbcType = JdbcType.BIGINT, id = true),
            Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            Result(column = "price", property = "price", jdbcType = JdbcType.DECIMAL),
            Result(many = Many(resultMap = "AttributesBaseResult"))
        ]
    )
    fun select(selectStatement: SelectStatementProvider): ItemWithAttributesBase

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "AttributesBaseResult", value = [
            Result(column = "attribute_id", property = "attributeId", jdbcType = JdbcType.BIGINT, id = true),
            Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            Result(column = "value", property = "value", jdbcType = JdbcType.VARCHAR),
            Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP)
        ]
    )
    fun selectAttributes(selectStatement: SelectStatementProvider): List<AttributesBase>
}