package com.bkjeon.base.v1.api.validation.controller

import com.bkjeon.base.feature.enums.common.ResponseResult
import com.bkjeon.base.feature.model.common.ApiResponse
import com.bkjeon.base.v1.api.validation.model.request.ValidationRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name="Validation API", description = "Validation API")
@RestController
@RequestMapping("/validation")
class ValidationController {

    @Operation(summary = "메인", description = "메인 API")
    @Parameters(
        Parameter(name = "param1", description = "Sample Parameter1", required = true),
        Parameter(name = "param2", description = "Sample Parameter2", required = true)
    )
    @GetMapping("/isParamValidChk")
    fun isApiValidCheck(@Valid request: ValidationRequest): ApiResponse<Any> {
        return ApiResponse(
            statusCode = HttpStatus.OK.value(),
            responseMessage = ResponseResult.SUCCESS.text,
            data = null
        )
    }

}