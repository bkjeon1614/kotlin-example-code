package com.bkjeon.base.feature.common.model.response

import com.bkjeon.base.feature.enums.common.ResponseResult
import org.springframework.http.HttpStatus

// API 공통 응답
data class ApiResponse<T>(
    // 상태 코드
    val statusCode: Int,

    // 응답 메시지
    val responseMessage: String?,

    // 응답 모델
    val data: T? = null
) {

    companion object {
        fun <T> ofSuccess(data: T): ApiResponse<T> {
            return ApiResponse(
                statusCode = HttpStatus.OK.value(),
                responseMessage = ResponseResult.SUCCESS.text,
                data = data
            )
        }

        fun <T> ofFail(data: T): ApiResponse<T> {
            return ApiResponse(
                statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                responseMessage = ResponseResult.FAIL.text,
                data = data
            )
        }
    }

}