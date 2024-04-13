package com.example.orderreception.error.handler

import com.example.orderreception.ResponseCode
import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException
import com.example.orderreception.openapi.model.ErrorData
import com.example.orderreception.openapi.model.ValidationErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalErrorHandler {
    @ExceptionHandler
    fun testExceptionHandler(ex: HttpMessageNotReadableException): ResponseEntity<ValidationErrorResponse> {
        return ResponseEntity(
            ValidationErrorResponse(
                ResponseCode.VALIDATION_ERROR.name,
                ErrorData(ex.message ?: "リクエスト形式が不正です。")
            ),
            HttpStatusCode.valueOf(400)
        )
    }

    @ExceptionHandler
    fun handleValidationException(ex: OrderReceptionIllegalArgumentException): ResponseEntity<ValidationErrorResponse> {
        return ResponseEntity(
            ValidationErrorResponse(
                ResponseCode.VALIDATION_ERROR.name,
                ErrorData(ex.validationErrors.joinToString(separator = "\n") { it.message })
            ),
            HttpStatus.BAD_REQUEST
        )
    }
}