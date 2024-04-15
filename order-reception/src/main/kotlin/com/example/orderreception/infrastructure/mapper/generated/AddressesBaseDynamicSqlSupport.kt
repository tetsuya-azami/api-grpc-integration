/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.orderreception.infrastructure.mapper.generated

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object AddressesBaseDynamicSqlSupport {
    val addressesBase = AddressesBase()

    val addressId = addressesBase.addressId

    val userId = addressesBase.userId

    val postcode = addressesBase.postcode

    val prefecture = addressesBase.prefecture

    val city = addressesBase.city

    val streetAddress = addressesBase.streetAddress

    val building = addressesBase.building

    val createdAt = addressesBase.createdAt

    val updatedAt = addressesBase.updatedAt

    class AddressesBase : AliasableSqlTable<AddressesBase>("addresses", ::AddressesBase) {
        val addressId = column<Long>(name = "address_id", jdbcType = JDBCType.BIGINT)

        val userId = column<Long>(name = "user_id", jdbcType = JDBCType.BIGINT)

        val postcode = column<String>(name = "postcode", jdbcType = JDBCType.VARCHAR)

        val prefecture = column<String>(name = "prefecture", jdbcType = JDBCType.VARCHAR)

        val city = column<String>(name = "city", jdbcType = JDBCType.VARCHAR)

        val streetAddress = column<String>(name = "street_address", jdbcType = JDBCType.VARCHAR)

        val building = column<String>(name = "building", jdbcType = JDBCType.VARCHAR)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}