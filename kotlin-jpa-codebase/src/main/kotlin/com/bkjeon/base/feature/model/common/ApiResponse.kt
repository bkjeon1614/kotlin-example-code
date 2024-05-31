package com.bkjeon.base.feature.model.common

import com.bkjeon.base.feature.enums.common.ResponseResult
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus

@Schema(description = "API 공통 응답")
data class ApiResponse<T>(
    @Schema(description = "상태 코드")
    val statusCode: Int,

    @Schema(description = "응답 메시지")
    val responseMessage: String?,

    @Schema(description = "응답 모델(제네릭)")
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
    }

}