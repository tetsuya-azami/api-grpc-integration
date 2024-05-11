package com.example.orderreception.domain.model.order

import com.example.orderreception.error.exception.IllegalMasterDataException

enum class BlackLevel(val code: Int) {
    LOW(0),
    MIDDLE(1),
    HIGH(2);

    companion object {
        fun reconstruct(userId: Long, code: Int): BlackLevel {
            return entries.firstOrNull { it.code == code }
                ?: throw IllegalMasterDataException("マスタデータのblackLevelに不正な値が混入している可能性があります。 userId: $userId, blackLevel: $code")
        }
    }
}