package com.example.orderreception.error.exception

import com.example.orderreception.error.ValidationError

data class OrderReceptionIllegalArgumentException(val validationErrors: List<ValidationError>) : Exception()