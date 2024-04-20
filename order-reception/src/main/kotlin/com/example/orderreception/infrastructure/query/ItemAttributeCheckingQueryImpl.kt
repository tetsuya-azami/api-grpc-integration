package com.example.orderreception.infrastructure.query

import com.example.orderreception.infrastructure.mapper.generated.ItemAttributesBaseMapper
import com.example.orderreception.infrastructure.mapper.generated.selectOne
import com.example.orderreception.usecase.query.ItemAttributeCheckingQuery
import org.mybatis.dynamic.sql.util.kotlin.model.select
import org.springframework.stereotype.Repository
import com.example.orderreception.infrastructure.mapper.generated.ItemAttributesBaseDynamicSqlSupport as sqlSupport

@Repository
class ItemAttributeCheckingQueryImpl(
    private val itemAttributesBaseMapper: ItemAttributesBaseMapper
) : ItemAttributeCheckingQuery {
    override fun existsItemAttribute(itemId: Long, attributeId: Long): Boolean {
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

        return attributesBase != null
    }
}