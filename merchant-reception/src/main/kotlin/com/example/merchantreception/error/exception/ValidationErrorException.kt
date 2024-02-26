package com.example.merchantreception.error.exception

data class ValidationErrorException(override val message: String) : RuntimeException()