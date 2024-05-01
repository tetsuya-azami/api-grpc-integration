package com.example.orderreception.infrastructure.query

import com.example.orderreception.domain.model.order.Address
import com.example.orderreception.infrastructure.mapper.generated.AddressesBaseMapper
import com.example.orderreception.infrastructure.mapper.generated.selectOne
import com.example.orderreception.usecase.query.AddressQuery
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.SqlBuilder.select
import org.springframework.stereotype.Repository
import com.example.orderreception.infrastructure.mapper.generated.AddressesBaseDynamicSqlSupport as sqlSupport

@Repository
class AddressQueryImpl(
    private val addressesBaseMapper: AddressesBaseMapper
) : AddressQuery {
    override fun findByAddressIdAndUserId(addressId: Long, userId: Long): Address? {
        val addressesBase = addressesBaseMapper.selectOne {
            select(
                sqlSupport.userId,
                sqlSupport.postcode,
                sqlSupport.prefecture,
                sqlSupport.city,
                sqlSupport.streetAddress,
                sqlSupport.building,
            ).from(sqlSupport.addressesBase)
                .where(sqlSupport.addressId, isEqualTo(addressId))
                .and(sqlSupport.userId, isEqualTo(userId))
        }

        return addressesBase?.let { Address.fromBase(addressesBase) }
    }
}