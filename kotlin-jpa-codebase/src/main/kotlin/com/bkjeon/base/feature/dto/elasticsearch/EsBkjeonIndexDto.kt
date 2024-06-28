package com.bkjeon.base.feature.dto.elasticsearch

data class EsBkjeonIndexDto(
    var esStartDate: String? = null,
    var esEndDate: String? = null,
    var title: String? = null,
    var content: String? = null,
    var author: String? = null,
    var age: Int? = null,
    var date: String? = null,
    var tags: List<String>? = null,
    var ageNoList: List<String>? = null,
    var constTest: String? = null,
)