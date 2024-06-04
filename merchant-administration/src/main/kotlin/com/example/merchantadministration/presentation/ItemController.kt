package com.example.merchantadministration.presentation

import com.example.grpcinterface.proto.ItemAttribute
import com.example.grpcinterface.proto.ItemAttributeServiceGrpcKt
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
        val itemWithSelectedAttributeIdsParams = request.itemIdsWithAttributeIdsList.map { item ->
            val selectedAttributeIdParams = item.selectedAttributeIdsList.map { attributeId ->
                SelectedAttributeIdParam.new(attributeId = attributeId)
            }
            ItemWithSelectedAttributeIdsParam.new(
                itemId = item.itemId,
                chainId = item.chainId,
                shopId = item.shopId,
                price = BigDecimal.valueOf(item.price.units),
                selectedAttributeIds = selectedAttributeIdParams
            )
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