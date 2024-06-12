package com.bkjeon.base.v1.api.dynamodb.controller

import com.bkjeon.base.feature.common.model.response.ApiResponse
import com.bkjeon.base.feature.dto.elasticsearch.EsResponseDto
import com.bkjeon.base.feature.entity.elasticsearch.BkjeonIndexAggs
import com.bkjeon.base.v1.api.elasticsearch.model.EsRequest
import com.bkjeon.base.v1.api.elasticsearch.service.ElasticsearchService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Elasticsearch API", description = "Elasticsearch 처리 관련 API")
@RestController
@RequestMapping("/elasticsearch")
class ElasticsearchController(
    val elasticsearchService: ElasticsearchService
) {

    @Operation(summary = "Elasticsearch 데이터 조회", description = "Elasticsearch 데이터 조회")
    @Parameters(
        Parameter(name = "startDate", description = "검색할 작성시작일자"),
        Parameter(name = "endDate", description = "검색할 작성종료일자"),
        Parameter(name = "title", description = "제목"),
        Parameter(name = "author", description = "작성자"),
        Parameter(name = "age", description = "나이"),
        Parameter(name = "ageNosStr", description = "특정값들(쉼표로 구분값), Ex: ageNosStr=5,7,13"),
    )
    @GetMapping("/samples")
    fun getList(request: EsRequest): ApiResponse<EsResponseDto> {
        return ApiResponse.ofSuccess(elasticsearchService.getSampleList(request))
    }

    @Operation(summary = "Elasticsearch 데이터 카운트 조회", description = "Elasticsearch 데이터 조회")
    @Parameters(
        Parameter(name = "title", description = "제목"),
        Parameter(name = "author", description = "작성자"),
        Parameter(name = "age", description = "나이"),
    )
    @GetMapping("/samples/totalCnt")
    fun getListTotalCnt(request: EsRequest): ApiResponse<Long> {
        return ApiResponse.ofSuccess(elasticsearchService.getSampleTotalCnt(request))
    }

    @Operation(summary = "Elasticsearch 데이터 aggregation 조회", description = "Elasticsearch 데이터 조회")
    @GetMapping("/aggsSamples")
    fun getAggsList(): ApiResponse<List<BkjeonIndexAggs>> {
        return ApiResponse.ofSuccess(elasticsearchService.getSampleAggsList())
    }

}