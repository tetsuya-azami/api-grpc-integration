package com.example.orderreception.error.exception

import com.example.orderreception.error.ValidationError

class OrderReceptionIllegalArgumentException(val validationErrors: List<ValidationError>) : Exception()