package com.example.orderreception.infrastructure.query

import com.example.orderreception.presentation.order.ItemParam
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
        val itemParams = listOf(
            ItemParam(itemId = 1, price = BigDecimal.valueOf(1), attributes = listOf(), quantity = 1),
            ItemParam(itemId = 2, price = BigDecimal.valueOf(1), attributes = listOf(), quantity = 1),
            ItemParam(itemId = 3, price = BigDecimal.valueOf(1), attributes = listOf(), quantity = 1)
        )

        val results = sut.findItems(itemParams, 1, 1).sortedBy { it.itemId }
        assertThat(results.size).isEqualTo(3)
        for ((index, result) in results.withIndex()) {
            assertThat(result.itemId).isEqualTo(index + 1L)
            assertThat(result.price).isEqualTo(BigDecimal.valueOf((index + 1) * 100L))
        }
    }
}