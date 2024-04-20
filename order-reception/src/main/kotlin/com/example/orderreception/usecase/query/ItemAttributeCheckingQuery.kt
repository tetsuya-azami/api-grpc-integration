package com.example.orderreception.usecase.query

import com.example.orderreception.error.ValidationError
import com.github.michaelbull.result.Result

interface ItemAttributeCheckingQuery {
    fun existsItemAttribute(itemId: Long, attributeId: Long): Result<Boolean, ValidationError>
}