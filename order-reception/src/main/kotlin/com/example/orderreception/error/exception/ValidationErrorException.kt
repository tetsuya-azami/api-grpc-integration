package com.example.orderreception.error.exception

data class ValidationErrorException(override val message: String) : RuntimeException()