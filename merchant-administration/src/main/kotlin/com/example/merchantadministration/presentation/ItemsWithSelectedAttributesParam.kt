package com.example.merchantadministration.presentation

import com.example.merchantadministration.error.ValidationError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import java.math.BigDecimal

data class ItemWithSelectedAttributeIdsParam private constructor(
    val itemId: Long,
    val chainId: Long,
    val shopId: Long,
    val price: BigDecimal,
    val selectedAttributeIds: List<SelectedAttributeIdParam>
) {
    companion object {
        fun new(
            itemId: Long?,
            chainId: Long?,
            shopId: Long?,
            price: BigDecimal?,
            selectedAttributeIds: List<SelectedAttributeIdParam>?
        ): Result<ItemWithSelectedAttributeIdsParam, List<ValidationError>> {
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
                        field = "chainId",
                        message = "チェーンIDがありません。"
                    )
                )
            }
            if (shopId == null) {
                validationErrors.add(
                    ValidationError(
                        field = "shopId",
                        message = "店舗IDがありません。"
                    )
                )
            }
            if (price == null) {
                validationErrors.add(
                    ValidationError(
                        field = "price",
                        message = "商品価格がありません。"
                    )
                )
            }
            if (selectedAttributeIds.isNullOrEmpty()) {
                validationErrors.add(
                    ValidationError(
                        field = "attributes",
                        message = "商品属性情報がありません。"
                    )
                )
            }

            if (validationErrors.isNotEmpty()) {
                return Err(validationErrors)
            }

            return Ok(
                ItemWithSelectedAttributeIdsParam(
                    itemId = itemId!!,
                    chainId = chainId!!,
                    shopId = shopId!!,
                    price = price!!,
                    selectedAttributeIds = selectedAttributeIds!!
                )
            )
        }
    }
}
