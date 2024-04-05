package com.example.orderprocessing.error.exception

import com.example.orderprocessing.error.ValidationError

class OrderProcessingIllegalArgumentException(val validationErrors: List<ValidationError>) : Exception()