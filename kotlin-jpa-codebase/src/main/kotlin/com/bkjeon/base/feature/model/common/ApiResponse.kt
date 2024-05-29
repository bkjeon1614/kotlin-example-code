package com.bkjeon.base.feature.model.common

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "API 공통 응답")
data class ApiResponse<T>(
    @Schema(description = "상태 코드")
    val statusCode: Int,

    @Schema(description = "응답 메시지")
    val responseMessage: String?,

    @Schema(description = "응답 모델(제네릭)")
    val data: T? = null
)