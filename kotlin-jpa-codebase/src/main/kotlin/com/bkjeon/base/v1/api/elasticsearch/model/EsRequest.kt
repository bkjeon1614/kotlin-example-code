package com.bkjeon.base.v1.api.elasticsearch.model

data class EsRequest(
    val title: String?,
    val author: String?,
    val age: Int?,
    val ageNosStr: String?,
    val startDate: String?,
    val endDate: String?
)