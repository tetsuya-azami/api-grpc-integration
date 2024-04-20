package com.example.orderreception.infrastructure.query

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import java.math.BigDecimal

@SpringBootTest
class ItemQueryImplTest @Autowired constructor(
    private val sut: ItemQueryImpl
) {

    @Test
    @Sql(
        scripts = ["sql/items_master.sql"],
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(
        scripts = ["sql/clear.sql"],
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    fun test() {
        val itemQueryParams = listOf(
            ItemQueryParam(itemId = 1, chainId = 1, shopId = 1),
            ItemQueryParam(itemId = 2, chainId = 1, shopId = 1),
            ItemQueryParam(itemId = 3, chainId = 1, shopId = 1),
        )

        val results = sut.findItems(itemQueryParams).sortedBy { it.itemId }
        assertThat(results.size).isEqualTo(3)
        for ((index, result) in results.withIndex()) {
            assertThat(result.itemId).isEqualTo(index + 1L)
            assertThat(result.price).isEqualTo(BigDecimal.valueOf((index + 1) * 100L))
        }
    }
}