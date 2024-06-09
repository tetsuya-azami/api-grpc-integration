package com.example.merchantadministration.presentation

import com.example.grpcinterface.proto.ItemAttribute
import com.example.grpcinterface.proto.ItemAttributeServiceGrpcKt
import com.example.merchantadministration.error.MerchantAdministrationIllegalArgumentException
import com.example.merchantadministration.usecase.query.ItemsWithSelectedAttributesCheckingQuery
import net.devh.boot.grpc.server.service.GrpcService
import org.slf4j.LoggerFactory
import java.math.BigDecimal

@GrpcService
@Suppress("unused")
class ItemController(
    private val itemsWithSelectedAttributesCheckingQuery: ItemsWithSelectedAttributesCheckingQuery
) : ItemAttributeServiceGrpcKt.ItemAttributeServiceCoroutineImplBase() {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override suspend fun checkItemsWithSelectedAttributes(request: ItemAttribute.CheckItemsWithSelectedAttributesRequest): ItemAttribute.CheckItemsWithSelectedAttributesResponse {
        val (itemWithSelectedAttributeIdsParams, validationErrors) = request.itemWithAttributeIdsList.map { item ->
            val selectedAttributeIdParams = item.selectedAttributeIdsList.map { attributeId ->
                SelectedAttributeIdParam.new(attributeId = attributeId)
            }
            // TODO: protocでgenerateした時点でnull許容型にしたい。
            ItemWithSelectedAttributeIdsParam.new(
                itemId = if (item.hasItemId()) item.itemId else null,
                chainId = if (item.hasChainId()) item.chainId else null,
                shopId = if (item.hasShopId()) item.shopId else null,
                price = if (item.hasPrice()) BigDecimal.valueOf(item.price.units) else null,
                selectedAttributeIds = selectedAttributeIdParams
            )
        }.partition { it.isOk }
            .let { (oks, errors) ->
                oks.map { it.value } to errors.flatMap { it.error }
            }

        if (validationErrors.isNotEmpty()) {
            logger.error("validation error: $validationErrors")
            throw MerchantAdministrationIllegalArgumentException(validationErrors)
        }

        itemsWithSelectedAttributesCheckingQuery.checkItemsWithSelectedAttributes(itemWithSelectedAttributeIdsParams)

        return ItemAttribute.CheckItemsWithSelectedAttributesResponse
            .newBuilder()
            .setResultCode(0) // 0: OK TODO: レスポンスの形式を考え直す
            .build()
            .let {
                logger.info("send response: $it")
                it
            }
    }
}