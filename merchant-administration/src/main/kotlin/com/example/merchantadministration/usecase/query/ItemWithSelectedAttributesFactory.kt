package com.example.merchantadministration.usecase.query

import com.example.merchantadministration.domain.readmodel.ItemWithAttributes
import com.example.merchantadministration.presentation.ItemWithSelectedAttributeIdsParam

interface ItemWithSelectedAttributesFactory {
    fun createItemWithSelectedAttributes(
        itemWithSelectedAttributeIdsParam: ItemWithSelectedAttributeIdsParam
    ): List<ItemWithAttributes>
}