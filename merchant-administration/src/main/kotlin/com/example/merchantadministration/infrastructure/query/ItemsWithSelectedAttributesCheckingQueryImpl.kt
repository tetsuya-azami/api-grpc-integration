package com.example.merchantadministration.infrastructure.query

import com.example.merchantadministration.error.MerchantAdministrationIllegalArgumentException
import com.example.merchantadministration.error.ValidationError
import com.example.merchantadministration.infrastructure.mapper.generated.ItemsBaseMapper
import com.example.merchantadministration.infrastructure.mapper.generated.selectOne
import com.example.merchantadministration.presentation.ItemWithSelectedAttributeIdsParam
import com.example.merchantadministration.usecase.query.ItemsWithSelectedAttributesCheckingQuery
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import com.example.merchantadministration.infrastructure.mapper.generated.ItemsBaseDynamicSqlSupport as items

@Repository
class ItemsWithSelectedAttributesCheckingQueryImpl(
    private val itemsBaseMapper: ItemsBaseMapper
) : ItemsWithSelectedAttributesCheckingQuery {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun checkItemsWithSelectedAttributes(itemWithSelectedAttributeIdsParams: List<ItemWithSelectedAttributeIdsParam>): Boolean {
        val validationErrors = mutableListOf<ValidationError>()
        itemWithSelectedAttributeIdsParams.forEach { param ->
            val item = itemsBaseMapper.selectOne {
                where {
                    items.itemId isEqualTo param.itemId
                    and { items.chainId isEqualTo param.chainId }
                    and { items.shopId isEqualTo param.shopId }
                }
            }
            if (item == null) {
                validationErrors.add(
                    ValidationError(
                        field = "items",
                        message = "存在しない商品です。itemId: ${param.itemId}, chainId: ${param.chainId}, shopId: ${param.shopId}"
                    )
                )
            } else {
                if (item.price == null) {
                    validationErrors.add(
                        ValidationError(
                            field = "items",
                            message = "マスタデータに価格が設定されていません。itemId: ${param.itemId}, chainId: ${param.chainId}, shopId: ${param.shopId}"
                        )
                    )
                } else if (item.price != param.price.toLong()) {
                    validationErrors.add(
                        ValidationError(
                            field = "price",
                            message = "マスタデータと価格が一致しません。itemId: ${param.itemId}, chainId: ${param.chainId}, shopId: ${param.shopId}, マスタデータのprice: ${item.price}, パラメータのprice: ${param.price}"
                        )
                    )
                }
            }
        }
        if (validationErrors.isNotEmpty()) {
            logger.warn("商品情報の整合性がありません。validationErrors: $validationErrors")
            throw MerchantAdministrationIllegalArgumentException(validationErrors)
        }

        return true
    }
}