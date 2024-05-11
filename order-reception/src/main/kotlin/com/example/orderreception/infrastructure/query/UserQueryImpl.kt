package com.example.orderreception.infrastructure.query

import com.example.orderreception.domain.model.order.User
import com.example.orderreception.infrastructure.mapper.generated.UsersBaseMapper
import com.example.orderreception.infrastructure.mapper.generated.selectByPrimaryKey
import com.example.orderreception.usecase.query.UserQuery
import org.springframework.stereotype.Repository

@Repository
class UserQueryImpl(
    private val usersBaseMapper: UsersBaseMapper
) : UserQuery {
    override fun findById(userId: Long): User? {
        val usersBase = usersBaseMapper.selectByPrimaryKey(userId)
        return usersBase?.let {
            User.reconstruct(
                id = usersBase.userId!!,
                blackLevel = usersBase.blackLevel!!
            )
        }
    }
}