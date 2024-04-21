package com.example.orderreception.domain.model.order

import com.example.orderreception.infrastructure.entity.generated.UsersBase

data class User private constructor(val id: Long, val blackLevel: BlackLevel) {
    companion object {
        fun fromBase(usersBase: UsersBase): User {
            return User(id = usersBase.userId!!, blackLevel = BlackLevel.fromBase(usersBase))
        }
    }

    fun isBlackUser(): Boolean {
        return when (blackLevel) {
            BlackLevel.LOW, BlackLevel.MIDDLE -> false
            BlackLevel.HIGH -> true
        }
    }
}