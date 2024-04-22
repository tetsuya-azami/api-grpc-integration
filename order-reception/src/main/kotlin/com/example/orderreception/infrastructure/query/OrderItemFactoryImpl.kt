package com.example.orderreception.infrastructure.query

import com.example.orderreception.domain.model.order.Item
import com.example.orderreception.error.ValidationError
import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException
import com.example.orderreception.infrastructure.mapper.custom.ItemAttributesMappler
import com.example.orderreception.presentation.order.AttributeParam
import com.example.orderreception.presentation.order.ItemParam
import com.example.orderreception.usecase.query.OrderItemFactory
import org.mybatis.dynamic.sql.SqlBuilder.*
import org.mybatis.dynamic.sql.render.RenderingStrategies
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.springframework.stereotype.Repository
import com.example.orderreception.infrastructure.mapper.generated.AttributesBaseDynamicSqlSupport as attributeSupport
import com.example.orderreception.infrastructure.mapper.generated.ItemAttributesBaseDynamicSqlSupport as itemAttributesSupport
import com.example.orderreception.infrastructure.mapper.generated.ItemsBaseDynamicSqlSupport as itemSupport


@Repository
class OrderItemFactoryImpl(
    private val itemAttributesMappler: ItemAttributesMappler
) : OrderItemFactory {
    override fun createOrderItems(itemParams: List<ItemParam>, chainId: Long, shopId: Long): List<Item> {
        if (itemParams.isEmpty()) throw OrderReceptionIllegalArgumentException(listOf(ValidationError("商品情報がありません。")))

        return itemParams.map { itemParam ->
            val selectStatementProvider =
                getSelectStatementProvider(itemParam.itemId, chainId, shopId, itemParam.attributes)
            val itemWithAttributesBase = itemAttributesMappler.select(selectStatementProvider)
            Item.fromBaseAndParam(itemWithAttributesBase, itemParam)
        }
    }

    private fun getSelectStatementProvider(
        itemId: Long,
        chainId: Long,
        shopId: Long,
        attributes: List<AttributeParam>
    ): SelectStatementProvider {

        var itemAttributesSelectStatement = select(
            itemAttributesSupport.itemId,
            itemAttributesSupport.attributeId
        ).from(
            itemAttributesSupport.itemAttributesBase
        ).where(
            itemAttributesSupport.attributeId, isEqualTo(attributes[0].attributeId)
        )

        for (attribute in attributes.drop(1)) {
            itemAttributesSelectStatement =
                itemAttributesSelectStatement.or(itemAttributesSupport.attributeId, isEqualTo(attribute.attributeId))
        }

        return select(
            itemSupport.itemId,
            itemSupport.name,
            itemSupport.price,
            attributeSupport.attributeId,
            attributeSupport.name,
            attributeSupport.value
        ).from(
            select(
                itemSupport.itemId,
                itemSupport.name,
                itemSupport.price
            ).from(
                itemSupport.itemsBase
            ).where(
                itemSupport.itemId, isEqualTo(itemId)
            ).and(
                itemSupport.chainId, isEqualTo(chainId)
            ).and(
                itemSupport.shopId, isEqualTo(shopId)
            ), "i"
        ).leftJoin(
            itemAttributesSelectStatement, "ia"
        ).on(itemSupport.itemId, equalTo(itemAttributesSupport.itemId))
            .leftJoin(attributeSupport.attributesBase)
            .on(itemAttributesSupport.itemAttributesBase.attributeId, equalTo(attributeSupport.attributeId))
            .build()
            .render(RenderingStrategies.MYBATIS3)
    }
}