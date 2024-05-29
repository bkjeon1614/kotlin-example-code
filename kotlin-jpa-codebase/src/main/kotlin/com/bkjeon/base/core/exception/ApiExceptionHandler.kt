package com.bkjeon.base.core.exception

import com.bkjeon.base.feature.constants.ResponseMsgConst
import com.bkjeon.base.feature.model.common.ApiResponse
import com.bkjeon.base.log.logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler {

    private val log = logger()

    @ExceptionHandler(Exception::class)
    fun handleBindException(e: Exception): ApiResponse<Any> {
        e.printStackTrace()
        log.error("=================== Exception Error !! $e")
        return ApiResponse(
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            responseMessage = ResponseMsgConst.INTERNAL_SERVER_ERROR,
            data = null
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleBindException(e: MethodArgumentNotValidException): ApiResponse<Any> {
        val message: String = e.bindingResult.fieldError?.defaultMessage
            ?: ResponseMsgConst.INTERNAL_SERVER_ERROR
        e.printStackTrace()
        log.error("=================== MethodArgumentNotValidException Error !! $e")
        return ApiResponse(
            statusCode = HttpStatus.FORBIDDEN.value(),
            responseMessage = message,
            data = null
        )
    }

}