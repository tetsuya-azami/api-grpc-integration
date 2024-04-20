package com.example.orderreception.usecase.query

interface ItemAttributeCheckingQuery {
    fun existsItemAttribute(itemId: Long, attributeId: Long): Boolean
}