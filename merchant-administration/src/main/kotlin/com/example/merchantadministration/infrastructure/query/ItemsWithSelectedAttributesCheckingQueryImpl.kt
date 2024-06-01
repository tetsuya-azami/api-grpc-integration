package com.example.merchantadministration.infrastructure.query

import com.example.merchantadministration.presentation.ItemWithSelectedAttributeIdsParam
import com.example.merchantadministration.usecase.query.ItemsWithSelectedAttributesCheckingQuery

class ItemsWithSelectedAttributesCheckingQueryImpl : ItemsWithSelectedAttributesCheckingQuery {
    override fun checkItemsWithSelectedAttributes(itemWithSelectedAttributeIdsParam: ItemWithSelectedAttributeIdsParam): Boolean {
        return true
    }
}