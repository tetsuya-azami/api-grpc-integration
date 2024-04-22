package com.example.orderreception.infrastructure.query

import com.example.orderreception.domain.model.order.OrderItem
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
    override fun createOrderItems(itemParams: List<ItemParam>, chainId: Long, shopId: Long): List<OrderItem> {
        if (itemParams.isEmpty()) throw OrderReceptionIllegalArgumentException(listOf(ValidationError("商品情報がありません。")))

        return itemParams.map { itemParam ->
            val selectStatementProvider =
                getSelectStatementProvider(itemParam.itemId, chainId, shopId, itemParam.attributes)
            val itemWithAttributesBase = itemAttributesMappler.select(selectStatementProvider)
            OrderItem.fromBaseAndParam(itemWithAttributesBase, itemParam)
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
            itemSupport.itemId.qualifiedWith("i"),
            itemSupport.name.qualifiedWith("i"),
            itemSupport.price.qualifiedWith("i"),
            attributeSupport.attributeId.qualifiedWith("a"),
            attributeSupport.name.qualifiedWith("a"),
            attributeSupport.value.qualifiedWith("a")
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
        ).on(itemSupport.itemId.qualifiedWith("i"), equalTo(itemAttributesSupport.itemId.qualifiedWith("ia")))
            .leftJoin(attributeSupport.attributesBase, "a")
            .on(
                itemAttributesSupport.itemAttributesBase.attributeId.qualifiedWith("ia"),
                equalTo(attributeSupport.attributeId.qualifiedWith("a"))
            )
            .build()
            .render(RenderingStrategies.MYBATIS3)
    }
}