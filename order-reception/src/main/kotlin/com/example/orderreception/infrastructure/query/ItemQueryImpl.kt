package com.example.orderreception.infrastructure.query

import com.example.orderreception.infrastructure.entity.generated.ItemsBase
import com.example.orderreception.infrastructure.mapper.generated.ItemsBaseMapper
import com.example.orderreception.usecase.query.ItemQuery
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.springframework.stereotype.Repository
import com.example.orderreception.infrastructure.mapper.generated.ItemsBaseDynamicSqlSupport as sqlSupport

@Repository
class ItemQueryImpl(
    private val itemsBaseMapper: ItemsBaseMapper
) : ItemQuery {
    override fun existsItem(itemId: Long, chainId: Long, shopId: Long): ItemsBase? {

        val itemsBase = itemsBaseMapper.selectOne(
            select(sqlSupport.itemId, sqlSupport.price) {
                where {
                    sqlSupport.itemId isEqualTo itemId
                    and {
                        sqlSupport.chainId isEqualTo chainId
                    }
                    and {
                        sqlSupport.shopId isEqualTo shopId
                    }
                }
            })

        return itemsBase
    }
}