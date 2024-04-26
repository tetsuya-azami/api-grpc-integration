package com.example.orderprocessing.error

interface ValidationError {
    val fieldName: String
    val description: String
}