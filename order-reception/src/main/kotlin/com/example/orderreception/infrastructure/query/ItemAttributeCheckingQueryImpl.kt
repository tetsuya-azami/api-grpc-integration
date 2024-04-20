package com.example.orderreception.infrastructure.query

import com.example.orderreception.error.ValidationError
import com.example.orderreception.infrastructure.mapper.generated.ItemAttributesBaseMapper
import com.example.orderreception.infrastructure.mapper.generated.selectOne
import com.example.orderreception.usecase.query.ItemAttributeCheckingQuery
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.mybatis.dynamic.sql.util.kotlin.model.select
import org.springframework.stereotype.Repository
import com.example.orderreception.infrastructure.mapper.generated.ItemAttributesBaseDynamicSqlSupport as sqlSupport

@Repository
class ItemAttributeCheckingQueryImpl(
    private val itemAttributesBaseMapper: ItemAttributesBaseMapper
) : ItemAttributeCheckingQuery {
    override fun existsItemAttribute(itemId: Long, attributeId: Long): Result<Boolean, ValidationError> {
        val attributesBase = itemAttributesBaseMapper.selectOne {
            select(sqlSupport.itemId) {
                where {
                    sqlSupport.itemId isEqualTo itemId
                    and {
                        sqlSupport.attributeId isEqualTo attributeId
                    }
                }
            }
        }

        return if (attributesBase != null) Ok(true)
        else Err(ValidationError(message = "商品情報と属性情報に整合性がありません。itemId=${itemId}, attributeId=${attributeId}"))
    }
}