package com.example.orderreception.infrastructure.query

import com.example.orderreception.domain.model.order.Item
import com.example.orderreception.error.ValidationError
import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException
import com.example.orderreception.infrastructure.mapper.generated.ItemsBaseMapper
import com.example.orderreception.usecase.query.ItemQuery
import org.mybatis.dynamic.sql.SqlBuilder.*
import org.mybatis.dynamic.sql.render.RenderingStrategies
import org.springframework.stereotype.Repository
import com.example.orderreception.infrastructure.mapper.generated.ItemsBaseDynamicSqlSupport as sqlSupport


@Repository
class ItemQueryImpl(
    private val itemsBaseMapper: ItemsBaseMapper
) : ItemQuery {
    override fun findItems(itemQueryParams: List<ItemQueryParam>): List<Item> {
        if (itemQueryParams.isEmpty()) throw OrderReceptionIllegalArgumentException(listOf(ValidationError("商品情報がありません。")))

        val selectStatement = select(sqlSupport.itemId, sqlSupport.price)
            .from(sqlSupport.itemsBase)
            .where(
                sqlSupport.itemId, isEqualTo(itemQueryParams[0].itemId),
                and(sqlSupport.chainId, isEqualTo(itemQueryParams[0].chainId)),
                and(sqlSupport.shopId, isEqualTo(itemQueryParams[0].shopId))
            )

        for (param in itemQueryParams.drop(1)) {
            selectStatement.or(
                sqlSupport.itemId, isEqualTo(param.itemId),
                and(sqlSupport.chainId, isEqualTo(param.chainId)),
                and(sqlSupport.shopId, isEqualTo(param.shopId))
            )
        }

        val itemsBases = itemsBaseMapper.selectMany(
            selectStatement.build()
                .render(RenderingStrategies.MYBATIS3)
        )

        return itemsBases.map { Item.fromBase(it) }
    }
}