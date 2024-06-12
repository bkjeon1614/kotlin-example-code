package com.bkjeon.base.feature.utils

import org.opensearch.client.json.JsonData
import org.opensearch.client.opensearch._types.FieldValue
import org.opensearch.client.opensearch._types.query_dsl.BoolQuery
import org.opensearch.client.opensearch._types.query_dsl.TermsQueryField

/**
 * Elasticsearch Common Query Builder Util
 */
object EsQueryDslCreateBuilderUtil {

    fun addFilterMatchQuery(requestQuery: BoolQuery, field: String, value: String): BoolQuery {
        return requestQuery.toBuilder()
            .filter { filter ->
                filter.match { match ->
                    match.field(field)
                    match.query(FieldValue.of(value))
                }
            }
            .build()
    }

    fun addFilterTermQuery(requestQuery: BoolQuery, field: String, value: String): BoolQuery {
        return requestQuery.toBuilder()
            .filter { filter ->
                filter.term { term ->
                    term.field(field)
                    term.value(FieldValue.of(value))
                }
            }
            .build()
    }

    fun addFilterTermsQuery(requestQuery: BoolQuery, field: String, values: List<String>): BoolQuery {
        return requestQuery.toBuilder()
            .filter { filter ->
                filter.terms { terms ->
                    terms.field(field)
                    terms.terms(TermsQueryField.of { termsQueryField ->
                        termsQueryField.value(values.mapNotNull { FieldValue.of(it) })
                    })
                }
            }
            .build()
    }

    fun addFilterRangeQuery(requestQuery: BoolQuery, field: String, from: String, to: String): BoolQuery {
        return requestQuery.toBuilder()
            .filter { filter ->
                filter.range { range ->
                    range.field(field)
                    range.from(JsonData.of(from))
                    range.to(JsonData.of(to))
                }
            }
            .build()
    }

}