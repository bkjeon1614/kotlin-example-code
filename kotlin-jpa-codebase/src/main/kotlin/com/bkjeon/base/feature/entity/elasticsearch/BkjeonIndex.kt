package com.bkjeon.base.feature.entity.elasticsearch

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class BkjeonIndex(
    @JsonProperty("title")
    val title: String?,

    @JsonProperty("content")
    val content: String?,

    @JsonProperty("author")
    val author: String?,

    @JsonProperty("age")
    val age: Int?,

    @JsonProperty("reg_date")
    val regDate: String?,

    @JsonProperty("tags")
    val tags: List<String>?
)