package com.example.orderprocessing.domain.model

import com.example.orderprocessing.helper.OrderTestHelper
import com.github.michaelbull.result.get
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class OrderTest {
    @Test
    fun インスタンス化_正常系() {
        // When
        val order = OrderTestHelper.getOrder()
        // Then
        assertThat(order.isOk).isTrue()
        val actual = order.get()!!
        assertThat(actual.orderItems.size()).isEqualTo(2)
        assertThat(actual.chainId).isEqualTo(1)
        assertThat(actual.shopId).isEqualTo(1)
        assertThat(actual.delivery.type).isEqualTo(DeliveryType.IMMEDIATE)
        assertThat(actual.delivery.addressId).isEqualTo(1)
        assertThat(actual.user.userId).isEqualTo(1)
        assertThat(actual.payment.paymentMethodType).isEqualTo(PaymentMethodType.CREDIT)
        assertThat(actual.payment.deliveryCharge).isEqualTo(BigDecimal.valueOf(100))
        assertThat(actual.payment.nonTaxedTotalPrice).isEqualTo(BigDecimal.valueOf(200))
        assertThat(actual.payment.tax).isEqualTo(BigDecimal.valueOf(30))
        assertThat(actual.payment.taxedTotalPrice).isEqualTo(BigDecimal.valueOf(330))
        assertThat(actual.blackLevel).isEqualTo(BlackLevel.LOW)
        assertThat(actual.time).isEqualTo(OrderTestHelper.now)
    }
}