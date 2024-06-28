package com.bkjeon.base.v1.api.elasticsearch.service

import com.bkjeon.base.feature.converter.elasticsearch.EsRequestConverter
import com.bkjeon.base.feature.dto.elasticsearch.EsBkjeonIndexDto
import com.bkjeon.base.feature.dto.elasticsearch.EsResponseDto
import com.bkjeon.base.feature.entity.elasticsearch.BkjeonIndexAggs
import com.bkjeon.base.feature.repository.elasticsearch.ElasticsearchSampleRepository
import com.bkjeon.base.v1.api.elasticsearch.model.EsRequest
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service

@Service
class ElasticsearchService(
    val esSampleRepository: ElasticsearchSampleRepository
) {
    companion object {
        val converter: EsRequestConverter = Mappers.getMapper(EsRequestConverter::class.java)
    }

    fun getSampleAggsList(): List<BkjeonIndexAggs> {
        return esSampleRepository.getAggsList(0, 10)
    }

    fun getSampleList(request: EsRequest): EsResponseDto {
        val esResponseDto: EsResponseDto = esSampleRepository.getList(
            EsBkjeonIndexDto(
                esStartDate = request.startDate,
                esEndDate = request.endDate,
                title = request.title,
                author = request.author,
                age = request.age,
                ageNoList = request.ageNosStr?.split(",")?.map { it.trim() }
            ),
            0,
            50
        )
        return esResponseDto
    }

    fun getSampleTotalCnt(request: EsRequest): Long {
        return esSampleRepository.getTotalCnt(converter.convertToEsBkjeonIndexDto(request))
    }

}