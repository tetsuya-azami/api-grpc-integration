package com.example.orderreception.error.handler

import com.example.orderreception.ResponseCode
import com.example.orderreception.error.exception.IllegalMasterDataException
import com.example.orderreception.error.exception.OrderReceptionIllegalArgumentException
import com.example.orderreception.openapi.model.ErrorData
import com.example.orderreception.openapi.model.ServerErrorResponse
import com.example.orderreception.openapi.model.ValidationErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalErrorHandler {

    private val logger = LoggerFactory.getLogger(this::class.java)

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

    @ExceptionHandler
    fun handleIllegalMasterDataException(ex: IllegalMasterDataException): ResponseEntity<ValidationErrorResponse> {
        logger.error("Illegal master data: ${ex.message}")
        return ResponseEntity(
            ValidationErrorResponse(
                ResponseCode.SERVER_ERROR.name,
                ErrorData("予期せぬエラーが発生しました。")
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler
    fun handleException(ex: Exception): ResponseEntity<ServerErrorResponse> {
        logger.error("Unexpected error: ${ex.message}")
        return ResponseEntity(
            ServerErrorResponse(
                ResponseCode.SERVER_ERROR.name,
                ErrorData("予期せぬエラーが発生しました。")
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}