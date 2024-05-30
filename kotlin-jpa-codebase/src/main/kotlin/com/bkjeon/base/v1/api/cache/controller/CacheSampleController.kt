package com.bkjeon.base.v1.api.cache.controller

import com.bkjeon.base.feature.enums.common.ResponseResult
import com.bkjeon.base.feature.model.common.ApiResponse
import com.bkjeon.base.v1.api.cache.model.request.CacheSampleRequest
import com.bkjeon.base.v1.api.cache.service.CacheSampleService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name="Cache API", description = "Cache API")
@RestController
@RequestMapping("/cache")
class CacheSampleController(
    val cacheSampleService: CacheSampleService
) {

    @Operation(summary = "캐시 API", description = "캐시 API")
    @GetMapping("/isCacheGet")
    fun isCacheGetList(request: CacheSampleRequest): ApiResponse<Any> {
        return ApiResponse(
            statusCode = HttpStatus.OK.value(),
            responseMessage = ResponseResult.SUCCESS.text,
            data = cacheSampleService.getCacheSampleList(request)
        )
    }

}