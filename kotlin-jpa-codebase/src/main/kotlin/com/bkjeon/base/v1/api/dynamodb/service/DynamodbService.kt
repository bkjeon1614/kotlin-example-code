package com.bkjeon.base.v1.api.dynamodb.service

import com.bkjeon.base.feature.entity.dynamodb.SampleTable
import com.bkjeon.base.feature.repository.dynamodb.DynamodbSampleRepository
import org.springframework.stereotype.Service

@Service
class DynamodbService(
    val dynamodbSampleRepository: DynamodbSampleRepository
) {

    fun getSampleList(): List<SampleTable> {
        return dynamodbSampleRepository.getSampleList()
    }

}