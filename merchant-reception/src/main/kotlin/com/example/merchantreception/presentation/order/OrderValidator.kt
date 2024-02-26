package com.example.merchantreception.presentation.order

import com.example.merchantreception.model.*
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class OrderValidator {

    fun isOrderValid(createOrderRequest: CreateOrderRequest): OrderValidationResult {
        val order = createOrderRequest.order
        if (isItemsValid(order.items) is OrderValidationResult.Invalid) {
            return isItemsValid(order.items)
        }
        if (isChainValid(order.chain) is OrderValidationResult.Invalid) {
            return isChainValid(order.chain)
        }
        if (isShopValid(order.shop) is OrderValidationResult.Invalid) {
            return isShopValid(order.shop)
        }
        if (isDeliveryValid(order.delivery) is OrderValidationResult.Invalid) {
            return isDeliveryValid(order.delivery)
        }
        if (isUserValid(order.user) is OrderValidationResult.Invalid) {
            return isUserValid(order.user)
        }
        if (isPaymentValid(order.payment) is OrderValidationResult.Invalid) {
            return isPaymentValid(order.payment)
        }
        try {
            LocalDateTime.parse(order.time, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+09:00"))
        } catch (e: Exception) {
            e.printStackTrace()
            return OrderValidationResult.invalid("注文日時はyyyy-mm-ddTHH:mm:ss+09:00の形式でなければなりません。")
        }
        return OrderValidationResult.valid()
    }

    private fun isItemsValid(items: List<Item>): OrderValidationResult {
        if (items.isEmpty() || items.size > 100) {
            return OrderValidationResult.invalid("注文商品は1個から100個の間で注文できます。")
        }
        for (item in items) {
            if (item.id == null || item.id < 1 || 999999999 < item.id) {
                return OrderValidationResult.invalid("商品IDは1から9999999999の間でなければなりません。 商品ID: ${item.id}")
            }
            if (item.name.isNullOrEmpty() || 250 < item.name.length) {
                return OrderValidationResult.invalid("商品名は100文字以内でなければなりません。 商品ID: ${item.id}")
            }
            if (item.price == null || item.price < 0 || 9999999999 < item.price) {
                return OrderValidationResult.invalid("商品価格は0から9999999999の間でなければなりません。 商品ID: ${item.id}")
            }
            if (item.quantity == null || item.quantity < 1 || 9999999999 < item.quantity) {
                return OrderValidationResult.invalid("購入数量は1から9999999999の間でなければなりません。 商品ID: ${item.id}")
            }

            val itemAttributesValidationResult = isItemAttributesValid(item)
            if (itemAttributesValidationResult is OrderValidationResult.Invalid) {
                return itemAttributesValidationResult
            }
        }

        return OrderValidationResult.valid()
    }

    private fun isItemAttributesValid(item: Item): OrderValidationResult {
        val attributes = item.attributes
        if (attributes.isNullOrEmpty() || 100 < attributes.size) {
            return OrderValidationResult.invalid("商品属性は0個から100個の間で商品に紐づけることができます。 商品ID: ${item.id}")
        }
        attributes.map {
            if (it.id == null || it.id < 1 || 9999999999 < it.id) {
                return OrderValidationResult.invalid("商品IDは1から9999999999の間でなければなりません。 商品ID: ${item.id}, 属性ID: ${it.id}")
            }
            if (it.name.isNullOrEmpty() || 250 < it.name.length) {
                return OrderValidationResult.invalid("商品属性名は1文字から250文字の間でなければなりません。商品ID: ${item.id}, 属性ID: ${it.id}")
            }
            if (it.value.isNullOrEmpty() || 250 < it.value.length) {
                return OrderValidationResult.invalid("商品属性値は1文字から250文字の間でなければなりません。商品ID: ${item.id}, 属性ID: ${it.id}")
            }
        }

        return OrderValidationResult.valid()
    }

    private fun isChainValid(chain: Chain): OrderValidationResult {
        if (chain.id == null || chain.id < 1 || 9999999999 < chain.id) {
            return OrderValidationResult.invalid("チェーンIDは1から9999999999の間でなければなりません。 チェーンID: ${chain.id}")
        }
        return OrderValidationResult.valid()
    }

    private fun isShopValid(shop: Shop): OrderValidationResult {
        if (shop.id == null || shop.id < 1 || 9999999999 < shop.id) {
            return OrderValidationResult.invalid("店舗IDは1から9999999999の間でなければなりません。 店舗ID: ${shop.id}")
        }
        return OrderValidationResult.valid()
    }

    private fun isDeliveryValid(delivery: Delivery): OrderValidationResult {
        if (delivery.type == null) {
            return OrderValidationResult.invalid("配送種別は${Delivery.Type.entries}のいずれかでなければなりません。")
        }
        if (delivery.addressId == null || delivery.addressId < 1 || 9999999999 < delivery.addressId) {
            return OrderValidationResult.invalid("配送先住所IDは1から9999999999の間でなければなりません。 配送先住所ID: ${delivery.addressId}")
        }
        return OrderValidationResult.valid()
    }

    private fun isUserValid(user: User): OrderValidationResult {
        if (user.id == null || user.id < 1 || 9999999999 < user.id) {
            return OrderValidationResult.invalid("ユーザIDは1から9999999999の間でなければなりません。 ユーザID: ${user.id}")
        }
        return OrderValidationResult.valid()
    }

    private fun isPaymentValid(payment: Payment): OrderValidationResult {
        if (payment.paymentMethod == null) {
            return OrderValidationResult.invalid("支払い方法は${Payment.PaymentMethod.entries}のいずれかでなければなりません。")
        }
        if (payment.deliveryCharge == null || payment.deliveryCharge < 0 || 9999999999 < payment.deliveryCharge) {
            return OrderValidationResult.invalid("配送料は0から9999999999の間でなければなりません。 配送料: ${payment.deliveryCharge}")
        }
        if (payment.nonTaxedTotalPrice == null || payment.nonTaxedTotalPrice < 0 || 9999999999 < payment.nonTaxedTotalPrice) {
            return OrderValidationResult.invalid("非課税合計金額は0から9999999999の間でなければなりません。 税抜き合計金額: ${payment.nonTaxedTotalPrice}")
        }
        if (payment.tax == null || payment.tax < 0 || 9999999999 < payment.tax) {
            return OrderValidationResult.invalid("消費税額は0から9999999999の間でなければなりません。 消費税額: ${payment.tax}")
        }
        if (payment.taxedTotalPrice == null || payment.taxedTotalPrice < 0 || 9999999999 < payment.taxedTotalPrice) {
            return OrderValidationResult.invalid("税込合計金額は0から9999999999の間でなければなりません。 税込み合計金額: ${payment.taxedTotalPrice}")
        }
        return OrderValidationResult.valid()
    }
}