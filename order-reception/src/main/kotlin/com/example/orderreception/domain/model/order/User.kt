package com.example.orderreception.domain.model.order

data class User private constructor(val id: Long, val blackLevel: BlackLevel) {
    companion object {
        fun reconstruct(
            id: Long,
            blackLevel: Int
        ): User {
            return User(
                id = id,
                blackLevel = BlackLevel.reconstruct(userId = id, code = blackLevel)
            )
        }
    }

    fun isBlackUser(): Boolean {
        return when (blackLevel) {
            BlackLevel.LOW, BlackLevel.MIDDLE -> false
            BlackLevel.HIGH -> true
        }
    }
}