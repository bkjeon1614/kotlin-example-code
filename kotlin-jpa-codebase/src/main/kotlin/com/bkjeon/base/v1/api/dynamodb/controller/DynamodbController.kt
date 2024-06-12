package com.bkjeon.base.v1.api.dynamodb.controller

import com.bkjeon.base.feature.entity.dynamodb.SampleTable
import com.bkjeon.base.v1.api.dynamodb.service.DynamodbService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "dynamodb API", description = "dynamodb 처리 관련 API")
@RestController
@RequestMapping("/dynamodb")
class DynamodbController(
    val dynamodbService: DynamodbService
) {

    @Operation(summary = "Dynamodb 데이터 조회", description = "Dynamodb 데이터 조회")
    @GetMapping("/samples")
    fun getList(): List<SampleTable> {
        return dynamodbService.getSampleList()
    }

}