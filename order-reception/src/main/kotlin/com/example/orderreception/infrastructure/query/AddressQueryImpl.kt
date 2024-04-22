package com.example.orderreception.infrastructure.query

import com.example.orderreception.domain.model.order.Address
import com.example.orderreception.infrastructure.mapper.generated.AddressesBaseMapper
import com.example.orderreception.infrastructure.mapper.generated.selectOne
import com.example.orderreception.usecase.query.AddressQuery
import org.mybatis.dynamic.sql.util.kotlin.model.select
import org.springframework.stereotype.Repository
import com.example.orderreception.infrastructure.mapper.generated.AddressesBaseDynamicSqlSupport as sqlSupport

@Repository
class AddressQueryImpl(
    private val addressesBaseMapper: AddressesBaseMapper
) : AddressQuery {
    override fun findById(addressId: Long, userId: Long): Address? {
        val addressesBase = addressesBaseMapper.selectOne {
            select(sqlSupport.addressId) {
                where {
                    sqlSupport.addressId isEqualTo addressId
                    and {
                        sqlSupport.userId isEqualTo userId
                    }
                }
            }
        }

        return addressesBase?.let { Address.fromBase(addressesBase) }
    }
}