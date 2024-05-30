package com.bkjeon.base.feature.repository.dynamodb

import com.bkjeon.base.core.log.logger
import com.bkjeon.base.feature.constants.CommonConst
import com.bkjeon.base.feature.constants.DdbIndexConst
import com.bkjeon.base.feature.entity.dynamodb.SampleTable
import com.bkjeon.base.feature.enums.dynamodb.DpDpFieldType
import org.springframework.stereotype.Repository
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.Expression
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional
import software.amazon.awssdk.services.dynamodb.model.AttributeValue

@Repository
class DynamodbSampleRepository(
    dynamoDbClient: DynamoDbEnhancedClient
) {
    private val table = dynamoDbClient.table(DdbIndexConst.SAMPLE_TABLE, TableSchema.fromBean(SampleTable::class.java))
    private val log = logger()

    fun getSampleList(): List<SampleTable> {
        val result = arrayListOf<SampleTable>()

        try {
            val expression = StringBuilder()
            val expressionValues = mutableMapOf<String, AttributeValue>()

            expression.append(DpDpFieldType.DEL_YN.expression)
            expressionValues[DpDpFieldType.DEL_YN.value] = AttributeValue.builder().s(CommonConst.NO).build()

            val queryConditional = QueryConditional.keyEqualTo(
                Key.builder().partitionValue(DpDpFieldType.TEST_COL1.name)
                    .build()
            )

            table.index(DdbIndexConst.GSI_DP_DP_01).query { r ->
                r.consistentRead(false)
                    .queryConditional(queryConditional)
                    .filterExpression(
                        Expression.builder().expression(expression.toString()).expressionValues(expressionValues).build()
                    )
            }.forEach { page ->
                page.items().forEach { item ->
                    result.add(item)
                }
            }
        } catch (e: Exception) {
            log.error("getSampleList: $e")
        }

        return result
    }

}