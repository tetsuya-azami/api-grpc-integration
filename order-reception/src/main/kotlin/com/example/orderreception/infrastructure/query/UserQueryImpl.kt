package com.example.orderreception.infrastructure.query

import com.example.orderreception.domain.model.order.BlackLevel
import com.example.orderreception.domain.model.order.User
import com.example.orderreception.domain.query.UserQuery
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseMapper
import com.example.orderreception.infrastructure.mapper.generated.selectByPrimaryKey
import org.springframework.stereotype.Repository

@Repository
class UserQueryImpl(val usersBaseMapper: UsersBaseMapper) : UserQuery {
    override fun findById(userId: Long): User? {
        val usersBase = usersBaseMapper.selectByPrimaryKey(userId)
        return usersBase?.let { User(it.userId!!, BlackLevel.fromBase(it)) }
    }
}