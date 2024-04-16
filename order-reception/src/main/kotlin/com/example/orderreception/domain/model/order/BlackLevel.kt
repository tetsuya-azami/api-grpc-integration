package com.example.orderreception.domain.model.order

import com.example.orderreception.error.exception.IllegalMasterDataException
import com.example.orderreception.infrastructure.entity.generated.UsersBase

enum class BlackLevel(val code: Int) {
    LOW(0),
    MIDDLE(1),
    HIGH(2);

    companion object {
        fun fromBase(usersBase: UsersBase): BlackLevel {
            return entries.firstOrNull { it.code == usersBase.blackLevel }
                ?: throw IllegalMasterDataException("マスタデータのblackLevelに不正な値が混入している可能性があります。 userId: ${usersBase.userId}, blackLevel: ${usersBase.blackLevel}")
        }
    }
}