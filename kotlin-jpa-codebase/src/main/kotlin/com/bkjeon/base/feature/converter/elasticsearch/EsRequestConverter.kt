package com.bkjeon.base.feature.converter.elasticsearch

import com.bkjeon.base.feature.dto.elasticsearch.EsBkjeonIndexDto
import com.bkjeon.base.v1.api.elasticsearch.model.EsRequest
import org.mapstruct.Mapper

@Mapper
interface EsRequestConverter {

    fun convertToEsBkjeonIndexDto(request: EsRequest): EsBkjeonIndexDto

}