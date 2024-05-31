package com.example.merchantadministration.presentation

import com.example.merchantadministration.error.MerchantAdministrationIllegalArgumentException
import com.example.merchantadministration.error.ValidationError

data class SelectedAttributeIdParam private constructor(
    val attributeId: Long,
) {
    companion object {
        fun new(
            attributeId: Long?,
        ): SelectedAttributeIdParam {
            if (attributeId == null) {
                throw MerchantAdministrationIllegalArgumentException(
                    listOf(ValidationError("attribute", "属性情報がありません。"))
                )
            }

            return SelectedAttributeIdParam(
                attributeId = attributeId
            )
        }
    }
}

