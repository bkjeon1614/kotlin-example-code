package com.bkjeon.base.feature.enums.dynamodb

enum class DpDpFieldType(
    val key: String,
    val value: String,
    val expression: String
) {
    DEL_YN("del_yn", ":del_yn", "del_yn = :del_yn"),
    TEST_COL1("test_col1", ":test_col1", "test_col1 = :test_col1")
}