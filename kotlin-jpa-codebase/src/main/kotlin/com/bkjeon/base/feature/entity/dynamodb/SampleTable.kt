package com.bkjeon.base.feature.entity.dynamodb

import com.bkjeon.base.feature.constants.DdbIndexConst
import org.springframework.format.annotation.DateTimeFormat
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*

@DynamoDbBean
data class SampleTable (
    // 파티션키
    @get:DynamoDbPartitionKey
    var hk: String? = null,

    // 정렬키
    @get:DynamoDbSortKey
    var sk: String? = null,

    // 글로벌 보조 인덱스 파티션키
    @get:DynamoDbSecondaryPartitionKey(indexNames = [DdbIndexConst.GSI_DP_DP_01])
    @get:DynamoDbAttribute(value = "gsi01_hk")
    var gsi01Hk: String? = null,

    // 글로벌 보조 인덱스 정렬키
    @get:DynamoDbSecondarySortKey(indexNames = [DdbIndexConst.GSI_DP_DP_01])
    @get:DynamoDbAttribute(value = "gsi01_sk")
    var gsi01Sk: String? = null,

    @get:DynamoDbAttribute(value = "title")
    var title: String? = null,

    @get:DynamoDbAttribute(value = "reg_dttm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var regDttm: String? = null
)