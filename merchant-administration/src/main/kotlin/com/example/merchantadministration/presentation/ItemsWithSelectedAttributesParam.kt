package com.example.merchantadministration.presentation

import com.example.merchantadministration.error.MerchantAdministrationIllegalArgumentException
import com.example.merchantadministration.error.ValidationError

data class ItemWithSelectedAttributeIdsParam private constructor(
    val itemId: Long,
    val chainId: Long,
    val shopId: Long,
    val selectedAttributeIds: List<SelectedAttributeIdParam>
) {
    companion object {
        fun new(
            itemId: Long?,
            chainId: Long?,
            shopId: Long?,
            selectedAttributeIds: List<SelectedAttributeIdParam>?
        ): ItemWithSelectedAttributeIdsParam {
            val validationErrors = mutableListOf<ValidationError>()

            if (itemId == null) {
                validationErrors.add(
                    ValidationError(
                        field = "item",
                        message = "商品IDがありません。"
                    )
                )
            }
            if (chainId == null) {
                validationErrors.add(
                    ValidationError(
                        field = "item",
                        message = "チェーンIDがありません。"
                    )
                )
            }
            if (shopId == null) {
                validationErrors.add(
                    ValidationError(
                        field = "item",
                        message = "店舗IDがありません。"
                    )
                )
            }
            if (selectedAttributeIds.isNullOrEmpty()) {
                validationErrors.add(
                    ValidationError(
                        field = "item",
                        message = "商品属性情報がありません。"
                    )
                )
            }

            if (validationErrors.isNotEmpty()) {
                throw MerchantAdministrationIllegalArgumentException(validationErrors = validationErrors)
            }

            return ItemWithSelectedAttributeIdsParam(
                itemId = itemId!!,
                chainId = chainId!!,
                shopId = shopId!!,
                selectedAttributeIds = selectedAttributeIds!!
            )
        }
    }
}
