package com.bkjeon.base.feature.enums.common

enum class ResponseResult(
    val text: String,
    val type: String
) {
    SUCCESS("성공", "SUCCESS"),
    FAIL("실패", "FAIL")
}