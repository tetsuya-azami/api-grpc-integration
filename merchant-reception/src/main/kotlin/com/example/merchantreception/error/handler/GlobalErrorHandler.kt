package com.example.merchantreception.error.handler

import com.example.merchantreception.ResponseCode
import com.example.merchantreception.error.exception.ValidationErrorException
import com.example.merchantreception.model.ErrorData
import com.example.merchantreception.model.ValidationErrorResponse
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
    fun handleValidationException(ex: ValidationErrorException): ResponseEntity<ValidationErrorResponse> {
        return ResponseEntity(
            ValidationErrorResponse(
                ResponseCode.VALIDATION_ERROR.name,
                ErrorData(ex.message)
            ),
            HttpStatus.BAD_REQUEST
        )
    }
}