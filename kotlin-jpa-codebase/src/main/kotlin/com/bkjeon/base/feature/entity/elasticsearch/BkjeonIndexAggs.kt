package com.bkjeon.base.feature.entity.elasticsearch

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BkjeonIndexAggs(
    val regDate: String?,
    val ageSum: Long?
)