package com.bkjeon.base.feature.dto.elasticsearch

data class EsBkjeonIndexDto(
    val startDate: String? = null,
    val endDate: String? = null,
    val title: String? = null,
    val content: String? = null,
    val author: String? = null,
    val age: Int? = null,
    val date: String? = null,
    val tags: List<String>? = null,
    val ageNoList: List<String>? = null
)