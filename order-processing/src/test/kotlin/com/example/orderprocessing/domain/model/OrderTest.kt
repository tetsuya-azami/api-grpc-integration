package com.example.orderprocessing.domain.model

import com.example.orderprocessing.helper.OrderTestHelper
import com.github.michaelbull.result.get
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class OrderTest {

    @Test
    fun インスタンス化_正常系() {
        // Given
        val orderParam = OrderTestHelper.createOrderParam()
        // When
        val order = Order.fromParam(orderParam = orderParam)
        // Then
        OrderTestHelper.assert_作成された注文モデルが正しいこと(orderParam, order.get()!!)
    }

    @Test
    fun インスタンス化_商品がない_異常系() {
        // Given
        val orderParam = OrderTestHelper.createOrderParam(itemParams = emptyList())
        // When
        val actual = Order.fromParam(orderParam = orderParam)
        // Then
        assertThat(actual.isErr).isTrue()
        val validationErrors = actual.error
        assertThat(validationErrors).hasSize(2)
        assertThat(validationErrors[0]).isEqualTo(OrderItems.OrderItemsValidationError.IllegalItemSize)
        assertThat(validationErrors[1]).isEqualTo(
            Order.OrderValidationErrors.IllegalNonTaxedTotalPrice(
                null,
                orderParam.paymentParam.nonTaxedTotalPrice
            )
        )
    }

    @Test
    fun インスタンス化_配達種別が不正_異常系() {
        // Given
        val orderParam = OrderTestHelper.createOrderParam(deliveryType = "invalid")
        // When
        val actual = Order.fromParam(orderParam = orderParam)
        // Then
        assertThat(actual.isErr).isTrue()
        val validationErrors = actual.error
        assertThat(validationErrors).hasSize(1)
        assertThat(validationErrors[0]).isEqualTo(DeliveryType.DeliveryTypeValidationErrors.IllegalDeliveryType("invalid"))
    }

    @Test
    fun インスタンス化_ブラックレベルが不正_異常系() {
        // Given
        val orderParam = OrderTestHelper.createOrderParam(blackLevel = "invalid")
        // When
        val actual = Order.fromParam(orderParam = orderParam)
        // Then
        assertThat(actual.isErr).isTrue()
        val validationErrors = actual.error
        assertThat(validationErrors).hasSize(1)
        assertThat(validationErrors[0]).isEqualTo(BlackLevel.BlackLevelValidationErrors.IllegalBlackLevel("invalid"))
    }

    @Test
    fun インスタンス化_支払い方法が不正_異常系() {
        // Given
        val orderParam = OrderTestHelper.createOrderParam(paymentMethod = "invalid")
        // When
        val actual = Order.fromParam(orderParam = orderParam)
        // Then
        assertThat(actual.isErr).isTrue()
        val validationErrors = actual.error
        assertThat(validationErrors).hasSize(1)
        assertThat(validationErrors[0]).isEqualTo(
            PaymentMethodType.PaymentMethodValidationErrors.IllegalPaymentMethodType(
                "invalid"
            )
        )
    }

    @Test
    fun インスタンス化_支払い金額が不正_異常系() {
        // Given
        val orderParam = OrderTestHelper.createOrderParam(nonTaxedTotalPrice = BigDecimal.valueOf(0))
        // When
        val actual = Order.fromParam(orderParam = orderParam)
        // Then
        assertThat(actual.isErr).isTrue()
        val validationErrors = actual.error
        assertThat(validationErrors).hasSize(3)
        assertThat(validationErrors[0]).isEqualTo(
            Payment.PaymentValidationError.IllegalTax(paymentParam = orderParam.paymentParam)
        )
        assertThat(validationErrors[1]).isEqualTo(
            Payment.PaymentValidationError.MissMatchTaxedTotalPrice(paymentParam = orderParam.paymentParam)
        )
        assertThat(validationErrors[2]).isEqualTo(
            Order.OrderValidationErrors.IllegalNonTaxedTotalPrice(
                OrderItems.fromParam(orderParam.orderItemParams).get()!!,
                orderParam.paymentParam.nonTaxedTotalPrice
            )
        )
    }
}