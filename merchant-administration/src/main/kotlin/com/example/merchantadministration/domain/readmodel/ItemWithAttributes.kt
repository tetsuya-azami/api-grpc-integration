package com.example.merchantadministration.domain.readmodel

import java.math.BigDecimal

data class ItemWithAttributes(
    val itemId: Long,
    val name: String,
    val price: BigDecimal,
    val attributes: List<Attribute>
) {
    data class Attribute(
        val id: Long,
        val name: String,
        val value: String
    )
}