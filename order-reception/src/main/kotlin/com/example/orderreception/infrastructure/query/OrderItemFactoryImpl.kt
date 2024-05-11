package com.example.orderreception.infrastructure.query

import com.example.orderreception.domain.model.order.Attribute
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
import org.slf4j.LoggerFactory
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

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun createOrderItems(orderItemParams: List<OrderItemParam>, chainId: Long, shopId: Long): List<OrderItem> {
        if (orderItemParams.isEmpty()) throw OrderReceptionIllegalArgumentException(
            listOf(
                ValidationError(
                    field = "orderItems",
                    message = "商品情報がありません。"
                )
            )
        )

        return orderItemParams.map { itemParam ->
            val selectStatementProvider =
                getSelectStatementProvider(itemParam.itemId, chainId, shopId, itemParam.attributes)
            val itemWithAttributesBase = itemAttributesMappler.select(selectStatementProvider)
                ?: run {
                    logger.warn("該当の商品または属性がありません。itemId: ${itemParam.itemId}, attributes: ${itemParam.attributes}, chainId: $chainId, shopId: $shopId")
                    throw OrderReceptionIllegalArgumentException(
                        listOf(
                            ValidationError(
                                field = "orderItems",
                                message = "商品情報の整合性がありません。"
                            )
                        )
                    )
                }
            // SQLで検索条件に渡した属性情報のうち一部が取得できなかった場合を検知
            if (itemWithAttributesBase.attributes.size != itemParam.attributes.size) {
                logger.warn("該当の商品または属性がありません。itemId: ${itemParam.itemId}, attributes: ${itemParam.attributes}")
                throw OrderReceptionIllegalArgumentException(
                    listOf(
                        ValidationError(
                            field = "orderItems",
                            message = "商品情報の整合性がありません。"
                        )
                    )
                )
            }

            if (itemParam.price != itemWithAttributesBase.price) {
                logger.warn("価格の不正操作もしくは注文時から商品マスタデータの更新が行われた可能性があります。itemId: ${itemParam.itemId}, パラメータのprice: ${itemParam.price}, マスタデータのprice: ${itemWithAttributesBase.price}")
                throw OrderReceptionIllegalArgumentException(
                    listOf(
                        ValidationError(
                            field = "price",
                            message = "価格の整合性がありません。"
                        )
                    )
                )
            }

            OrderItem.reconstruct(
                itemId = itemWithAttributesBase.itemId!!,
                name = itemWithAttributesBase.name!!,
                price = itemWithAttributesBase.price!!,
                attributes = itemWithAttributesBase.attributes.map {
                    Attribute.reconstruct(
                        id = it.attributeId!!,
                        name = it.name!!,
                        value = it.value!!
                    )
                },
                quantity = itemParam.quantity,
            )
        }
    }

    // TODO: SQL直書きしたい（複雑なクエリだとdynamic-sqlは見づらい）
    // もしくはViewなどを使用する？
    private fun getSelectStatementProvider(
        itemId: Long,
        chainId: Long,
        shopId: Long,
        attributeParams: List<AttributeParam>
    ): SelectStatementProvider {

        if (attributeParams.isEmpty()) {
            // 注文商品に属性情報がない場合
            return select(
                i.itemId,
                i.name,
                i.price
            ).from(items)
                .where(
                    i.itemId, isEqualTo(itemId)
                ).and(
                    i.chainId, isEqualTo(chainId)
                ).and(
                    i.shopId, isEqualTo(shopId)
                )
                .build()
                .render(RenderingStrategies.MYBATIS3)
        } else {
            // 注文商品に属性情報がある場合
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
}