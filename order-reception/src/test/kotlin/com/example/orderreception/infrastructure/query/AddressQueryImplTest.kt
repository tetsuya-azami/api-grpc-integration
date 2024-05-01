package com.example.orderreception.infrastructure.query

import com.example.orderreception.infrastructure.entity.generated.AddressesBase
import com.example.orderreception.infrastructure.mapper.generated.AddressesBaseMapper
import com.example.orderreception.infrastructure.mapper.generated.delete
import com.example.orderreception.infrastructure.mapper.generated.insertMultiple
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@SpringBootTest
@Transactional
class AddressQueryImplTest(
    @Autowired private val addressesBaseMapper: AddressesBaseMapper,
    @Autowired private val addressQueryImpl: AddressQueryImpl
) {

    companion object {
        val now = LocalDateTime.of(2000, 1, 2, 3, 4, 5)
    }

    @BeforeEach
    fun clear() {
        addressesBaseMapper.delete { allRows() }
    }

    @Test
    fun 渡された配達先住所IDおよびユーザIDで対象のデータを取得できること() {
        // given
        val expected = getAddressesBase(addressId = 1, userId = 1)
        val notSelected = getAddressesBase(addressId = 2, userId = 1)
        addressesBaseMapper.insertMultiple(listOf(expected, notSelected))

        // when
        val actual =
            addressQueryImpl.findByAddressIdAndUserId(expected.addressId!!, expected.userId!!)

        assertThat(actual?.userId).isEqualTo(expected.userId)
        assertThat(actual?.postcode).isEqualTo(expected.postcode)
        assertThat(actual?.prefecture).isEqualTo(expected.prefecture)
        assertThat(actual?.city).isEqualTo(expected.city)
        assertThat(actual?.streetAddress).isEqualTo(expected.streetAddress)
        assertThat(actual?.building).isEqualTo(expected.building)
    }

    private fun getAddressesBase(
        addressId: Long = 1L,
        userId: Long = 1L,
        postcode: String = "111-1111",
        prefecture: String = "群馬県",
        city: String = "高崎市",
        streetAddress: String = "旭町",
        building: String = "建物",
        createdAt: LocalDateTime = now,
        updatedAt: LocalDateTime = now
    ): AddressesBase {
        return AddressesBase(
            addressId = addressId,
            userId = userId,
            postcode = postcode,
            prefecture = prefecture,
            city = city,
            streetAddress = streetAddress,
            building = building,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}