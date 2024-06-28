package com.bkjeon.base.v1.api.elasticsearch.model

data class EsRequest(
    var title: String?,
    var author: String?,
    var age: Int?,
    var ageNosStr: String?,
    var startDate: String?,
    var endDate: String?
)