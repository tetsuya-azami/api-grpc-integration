package com.example.orderreception.infrastructure.query

import com.example.orderreception.domain.model.order.OrderItem
import com.example.orderreception.error.ValidationError
import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException
import com.example.orderreception.infrastructure.mapper.custom.ItemAttributesMappler
import com.example.orderreception.presentation.order.AttributeParam
import com.example.orderreception.presentation.order.OrderItemParam
import com.example.orderreception.usecase.query.OrderItemFactory
import org.mybatis.dynamic.sql.SqlBuilder.*
import org.mybatis.dynamic.sql.render.RenderingStrategies
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.springframework.stereotype.Repository
import com.example.orderreception.infrastructure.mapper.generated.AttributesBaseDynamicSqlSupport as a
import com.example.orderreception.infrastructure.mapper.generated.AttributesBaseDynamicSqlSupport.attributesBase as attributes
import com.example.orderreception.infrastructure.mapper.generated.ItemAttributesBaseDynamicSqlSupport as ia
import com.example.orderreception.infrastructure.mapper.generated.ItemAttributesBaseDynamicSqlSupport.itemAttributesBase as itemAttributes
import com.example.orderreception.infrastructure.mapper.generated.ItemsBaseDynamicSqlSupport as i
import com.example.orderreception.infrastructure.mapper.generated.ItemsBaseDynamicSqlSupport.itemsBase as items


@Repository
class OrderItemFactoryImpl(
    private val itemAttributesMappler: ItemAttributesMappler
) : OrderItemFactory {
    override fun createOrderItems(orderItemParams: List<OrderItemParam>, chainId: Long, shopId: Long): List<OrderItem> {
        if (orderItemParams.isEmpty()) throw OrderReceptionIllegalArgumentException(listOf(ValidationError("商品情報がありません。")))

        return orderItemParams.map { itemParam ->
            val selectStatementProvider =
                getSelectStatementProvider(itemParam.itemId, chainId, shopId, itemParam.attributes)
            val itemWithAttributesBase = itemAttributesMappler.select(selectStatementProvider)
            OrderItem.fromBaseAndParam(itemWithAttributesBase, itemParam)
        }
    }

    // TODO: SQL直書きしたい（複雑なクエリだとdynamic-sqlは見づらい）
    private fun getSelectStatementProvider(
        itemId: Long,
        chainId: Long,
        shopId: Long,
        attributeParams: List<AttributeParam>
    ): SelectStatementProvider {

        var itemAttributesSelectStatement = select(
            ia.itemId,
            ia.attributeId
        ).from(
            itemAttributes
        ).where(
            ia.attributeId, isEqualTo(attributeParams[0].attributeId)
        )

        for (attribute in attributeParams.drop(1)) {
            itemAttributesSelectStatement =
                itemAttributesSelectStatement.or(ia.attributeId, isEqualTo(attribute.attributeId))
        }

        return select(
            i.itemId.qualifiedWith("i"),
            i.name.qualifiedWith("i"),
            i.price.qualifiedWith("i"),
            a.attributeId.qualifiedWith("a").`as`("a_attribute_id"),
            a.name.qualifiedWith("a").`as`("a_name"),
            a.value.qualifiedWith("a").`as`("a_value")
        ).from(
            select(
                i.itemId,
                i.name,
                i.price
            ).from(
                items
            ).where(
                i.itemId, isEqualTo(itemId)
            ).and(
                i.chainId, isEqualTo(chainId)
            ).and(
                i.shopId, isEqualTo(shopId)
            ), "i"
        ).leftJoin(
            itemAttributesSelectStatement, "ia"
        ).on(
            i.itemId.qualifiedWith("i"),
            equalTo(ia.itemId.qualifiedWith("ia"))
        ).leftJoin(attributes, "a")
            .on(
                itemAttributes.attributeId.qualifiedWith("ia"),
                equalTo(a.attributeId.qualifiedWith("a"))
            )
            .build()
            .render(RenderingStrategies.MYBATIS3)
    }
}