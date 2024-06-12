package com.bkjeon.base.feature.dto.elasticsearch

import com.bkjeon.base.feature.entity.elasticsearch.BkjeonIndex

data class EsResponseDto (
    val totalCnt: Long?,
    val list: List<BkjeonIndex>
)