package com.example.orderreception.infrastructure.query

import com.example.orderreception.presentation.order.AttributeParam
import com.example.orderreception.presentation.order.OrderItemParam
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import java.math.BigDecimal

@SpringBootTest
class OrderItemFactoryImplTest @Autowired constructor(
    private val sut: OrderItemFactoryImpl
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
        val orderItemParams = listOf(
            OrderItemParam(
                itemId = 1,
                price = BigDecimal.valueOf(100),
                attributes = listOf(AttributeParam(attributeId = 1)),
                quantity = 1
            ),
            OrderItemParam(
                itemId = 2,
                price = BigDecimal.valueOf(200),
                attributes = listOf(
                    AttributeParam(attributeId = 2),
                    AttributeParam(attributeId = 3)
                ),
                quantity = 2
            ),
            OrderItemParam(
                itemId = 3,
                price = BigDecimal.valueOf(300),
                attributes = listOf(
                    AttributeParam(attributeId = 2),
                    AttributeParam(attributeId = 3),
                    AttributeParam(attributeId = 4),
                ),
                quantity = 3
            )
        )

        val results = sut.createOrderItems(orderItemParams, 1, 1).sortedBy { it.itemId }
        assertThat(results.size).isEqualTo(3)
        for ((index, result) in results.withIndex()) {
            assertThat(result.itemId).isEqualTo(index + 1L)
            assertThat(result.price).isEqualTo(BigDecimal.valueOf((index + 1) * 100L))
        }
    }
}