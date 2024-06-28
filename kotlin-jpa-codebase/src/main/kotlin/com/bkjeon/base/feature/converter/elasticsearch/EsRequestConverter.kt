package com.bkjeon.base.feature.converter.elasticsearch

import com.bkjeon.base.feature.constants.CommonConst
import com.bkjeon.base.feature.dto.elasticsearch.EsBkjeonIndexDto
import com.bkjeon.base.v1.api.elasticsearch.model.EsRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface EsRequestConverter {

    @Mappings(
        Mapping(source = "startDate", target = "esStartDate"),
        Mapping(source = "endDate", target = "esEndDate"),
        Mapping(target = "constTest", constant = CommonConst.YES)
    )
    fun convertToEsBkjeonIndexDto(request: EsRequest): EsBkjeonIndexDto

}