package com.example.orderprocessing.domain.model

import com.example.orderprocessing.error.ValidationError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

enum class BlackLevel(val code: Int) {
    LOW(0),
    MIDDLE(1),
    HIGH(2);

    companion object {
        fun fromString(value: String): Result<BlackLevel, List<ValidationError>> {
            return entries.firstOrNull { it.name == value.uppercase() }
                ?.let { Ok(it) }
                ?: Err(listOf(BlackLevelValidationErrors.IllegalBlackLevel(value)))
        }
    }

    sealed interface BlackLevelValidationErrors : ValidationError {
        data class IllegalBlackLevel(val value: String) : BlackLevelValidationErrors {
            override val fieldName: String
                get() = BlackLevel::class.simpleName!!
            override val description: String
                get() = "ブラックレベルは${BlackLevel.entries}の中から選んでください。ブラックレベル: $value"
        }
    }
}