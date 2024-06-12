package com.bkjeon.base.feature.enums.elasticsearch

enum class EsBkjeonIndexType(
    val field: String
) {
    TITLE("title"),
    AUTHOR("author"),
    AGE("age"),
    REG_DATE("reg_date")
}