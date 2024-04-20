package com.example.orderreception.infrastructure.query

import com.example.orderreception.domain.model.order.Item
import com.example.orderreception.error.ValidationError
import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException
import com.example.orderreception.infrastructure.mapper.generated.ItemsBaseMapper
import com.example.orderreception.presentation.order.ItemParam
import com.example.orderreception.usecase.query.ItemQuery
import org.mybatis.dynamic.sql.SqlBuilder.*
import org.mybatis.dynamic.sql.render.RenderingStrategies
import org.springframework.stereotype.Repository
import com.example.orderreception.infrastructure.mapper.generated.ItemsBaseDynamicSqlSupport as sqlSupport


@Repository
class ItemQueryImpl(
    private val itemsBaseMapper: ItemsBaseMapper
) : ItemQuery {
    override fun findItems(itemParams: List<ItemParam>, chainId: Long, shopId: Long): List<Item> {
        if (itemParams.isEmpty()) throw OrderReceptionIllegalArgumentException(listOf(ValidationError("商品情報がありません。")))

        val selectStatement = select(sqlSupport.itemId, sqlSupport.price)
            .from(sqlSupport.itemsBase)
            .where(
                sqlSupport.itemId, isEqualTo(itemParams[0].itemId),
                and(sqlSupport.chainId, isEqualTo(chainId)),
                and(sqlSupport.shopId, isEqualTo(shopId))
            )

        for (item in itemParams.drop(1)) {
            selectStatement.or(
                sqlSupport.itemId, isEqualTo(item.itemId),
                and(sqlSupport.chainId, isEqualTo(chainId)),
                and(sqlSupport.shopId, isEqualTo(shopId))
            )
        }

        val itemsBases = itemsBaseMapper.selectMany(
            selectStatement.build()
                .render(RenderingStrategies.MYBATIS3)
        )

        return itemsBases.map { Item.fromBase(it) }
    }
}