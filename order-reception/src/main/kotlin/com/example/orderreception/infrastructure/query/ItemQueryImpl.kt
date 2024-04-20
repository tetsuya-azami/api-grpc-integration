package com.example.orderreception.infrastructure.query

import com.example.orderreception.infrastructure.entity.generated.ItemsBase
import com.example.orderreception.infrastructure.mapper.generated.ItemsBaseDynamicSqlSupport
import com.example.orderreception.infrastructure.mapper.generated.ItemsBaseMapper
import com.example.orderreception.usecase.query.ItemQuery
import org.mybatis.dynamic.sql.SqlBuilder.select
import org.mybatis.dynamic.sql.render.RenderingStrategies
import org.mybatis.dynamic.sql.util.kotlin.elements.concat
import org.mybatis.dynamic.sql.util.kotlin.elements.isIn
import org.springframework.stereotype.Repository


@Repository
class ItemQueryImpl(
    private val itemsBaseMapper: ItemsBaseMapper
) : ItemQuery {
    override fun findItems(itemId: Long, chainId: Long, shopId: Long): List<ItemsBase> {

        return itemsBaseMapper.selectMany(
            select(ItemsBaseDynamicSqlSupport.itemId, ItemsBaseDynamicSqlSupport.price)
                .from(ItemsBaseDynamicSqlSupport.itemsBase)
                .where(
                    concat(
                        ItemsBaseDynamicSqlSupport.itemId,
                        ItemsBaseDynamicSqlSupport.chainId,
                        ItemsBaseDynamicSqlSupport.shopId
                    ), isIn(itemId, chainId, shopId)
                )
                .build()
                .render(RenderingStrategies.MYBATIS3)
        )
    }
}