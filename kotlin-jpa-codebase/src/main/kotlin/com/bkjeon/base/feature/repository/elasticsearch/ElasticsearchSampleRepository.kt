package com.bkjeon.base.feature.repository.elasticsearch

import com.bkjeon.base.core.log.logger
import com.bkjeon.base.feature.constants.CommonConst
import com.bkjeon.base.feature.constants.EsIndexConst
import com.bkjeon.base.feature.dto.elasticsearch.EsBkjeonIndexDto
import com.bkjeon.base.feature.dto.elasticsearch.EsResponseDto
import com.bkjeon.base.feature.entity.elasticsearch.BkjeonIndex
import com.bkjeon.base.feature.entity.elasticsearch.BkjeonIndexAggs
import com.bkjeon.base.feature.enums.elasticsearch.EsBkjeonIndexType
import com.bkjeon.base.feature.utils.EsQueryDslCreateBuilderUtil
import com.google.gson.Gson
import org.apache.commons.lang3.StringUtils
import org.opensearch.client.opensearch.OpenSearchClient
import org.opensearch.client.opensearch._types.FieldSort
import org.opensearch.client.opensearch._types.SortOptions
import org.opensearch.client.opensearch._types.SortOrder
import org.opensearch.client.opensearch._types.aggregations.Aggregation
import org.opensearch.client.opensearch._types.aggregations.BucketSortAggregation
import org.opensearch.client.opensearch._types.query_dsl.BoolQuery
import org.opensearch.client.opensearch.core.CountRequest
import org.opensearch.client.opensearch.core.SearchRequest
import org.opensearch.client.opensearch.core.search.TrackHits
import org.springframework.stereotype.Repository

@Repository
class ElasticsearchSampleRepository(
    val openSearchClient: OpenSearchClient
) {
    private val log = logger()

    companion object {
        private const val INDEX_NAME = EsIndexConst.BKJEON_INDEX

        private const val BUCKET_SORT_NAME = "age_bucket_sort"
        private val AUTHOR_AGGR_KEY = EsBkjeonIndexType.AUTHOR.field + CommonConst.AGGS_SUFFIX
        private val AGE_SUM_AGGR_KEY = EsBkjeonIndexType.AGE.field + CommonConst.AGGS_SUM_SUFFIX
    }

    fun getAggsList(offset: Int, size: Int): List<BkjeonIndexAggs> {
        val result: MutableList<BkjeonIndexAggs> = ArrayList()

        val ageSumAggs: Aggregation = Aggregation.of { aggs ->
            aggs.sum { sum ->
                sum.field(EsBkjeonIndexType.AGE.field)
            }
        }

        // bucket sort
        val bucketSort: Aggregation = Aggregation.of { aggr ->
            aggr.bucketSort(BucketSortAggregation.of { bucketSortAggregation ->
                bucketSortAggregation.sort(SortOptions.of { sortOptions ->
                    sortOptions.field(FieldSort.of { fieldSort ->
                        fieldSort.field(AGE_SUM_AGGR_KEY)
                        fieldSort.order(SortOrder.Desc)
                    })
                })
                bucketSortAggregation.from(offset)
                bucketSortAggregation.size(size)
            })
        }

        val regDateAggs: Aggregation = Aggregation.of { aggr ->
            aggr.terms { terms ->
                terms.field(EsBkjeonIndexType.AUTHOR.field).size(size)
            }.aggregations(AGE_SUM_AGGR_KEY, ageSumAggs)
                .aggregations(BUCKET_SORT_NAME, bucketSort)
        }

        try {
            val request = SearchRequest.of { searchRequest ->
                searchRequest.index(INDEX_NAME)
                searchRequest.size(0)
                searchRequest.aggregations(AUTHOR_AGGR_KEY, regDateAggs)
            }
            val response = openSearchClient.search(request, BkjeonIndexAggs::class.java)
            val aggregate = response.aggregations()[AUTHOR_AGGR_KEY]
            aggregate?.sterms()?.buckets()?.array()?.forEach { bucket ->
                if (StringUtils.isNotEmpty(bucket.key())) {
                    result.add(
                        BkjeonIndexAggs(
                            regDate = bucket.key(),
                            ageSum = bucket.aggregations()[AGE_SUM_AGGR_KEY]?.sum()?.value()?.toLong() ?: 0
                        )
                    )
                }
            }
        } catch (e: Exception) {
            log.error("getAggsList Error!! : $e")
            return emptyList()
        }

        return result
    }

    fun getList(
        esBkjeonIndexDto: EsBkjeonIndexDto,
        queryOffset: Int,
        querySize: Int
    ): EsResponseDto {
        var sampleList: List<BkjeonIndex> = arrayListOf()
        var totalCnt = 0L

        var requestQuery = BoolQuery.Builder().build()

        esBkjeonIndexDto.title?.let {
            requestQuery = EsQueryDslCreateBuilderUtil.addFilterMatchQuery(requestQuery, EsBkjeonIndexType.TITLE.field,
                it)
        }

        esBkjeonIndexDto.author?.let {
            requestQuery = EsQueryDslCreateBuilderUtil.addFilterTermQuery(requestQuery, EsBkjeonIndexType.AUTHOR.field,
                it)
        }

        esBkjeonIndexDto.ageNoList?.let {
            requestQuery = EsQueryDslCreateBuilderUtil.addFilterTermsQuery(requestQuery, EsBkjeonIndexType.AGE.field,
                it)
        }

        if (esBkjeonIndexDto.startDate != null && esBkjeonIndexDto.endDate != null) {
            requestQuery = EsQueryDslCreateBuilderUtil.addFilterRangeQuery(
                requestQuery,
                EsBkjeonIndexType.REG_DATE.field,
                esBkjeonIndexDto.startDate,
                esBkjeonIndexDto.endDate
            )
        }

        val request = SearchRequest.of { searchRequest ->
            searchRequest.index(INDEX_NAME)
            searchRequest.from(queryOffset)
            searchRequest.size(querySize)
            searchRequest.trackTotalHits(TrackHits.of { trackHits ->
                trackHits.enabled(true)
            })
            searchRequest.query { query ->
                query.bool(requestQuery)
            }
        }
        println(Gson().toJson(request))

        try {
            val result = openSearchClient.search(request, BkjeonIndex::class.java)
            sampleList = result.hits().hits().mapNotNull { it.source() } as ArrayList<BkjeonIndex>
            totalCnt = result.hits().total().value()
        } catch (e: Exception) {
            log.error("getSampleList: $e")
        }

        return EsResponseDto(totalCnt = totalCnt, list = sampleList)
    }

    fun getTotalCnt(esBkjeonIndexDto: EsBkjeonIndexDto): Long {
        var result: Long = 0

        var requestQuery = BoolQuery.Builder().build()

        esBkjeonIndexDto.title?.let {
            requestQuery = EsQueryDslCreateBuilderUtil.addFilterMatchQuery(requestQuery, EsBkjeonIndexType.TITLE.field,
                it)
        }

        esBkjeonIndexDto.author?.let {
            requestQuery = EsQueryDslCreateBuilderUtil.addFilterTermQuery(requestQuery, EsBkjeonIndexType.AUTHOR.field,
                it)
        }

        esBkjeonIndexDto.ageNoList?.let {
            requestQuery = EsQueryDslCreateBuilderUtil.addFilterTermsQuery(requestQuery, EsBkjeonIndexType.AGE.field,
                it)
        }

        if (esBkjeonIndexDto.startDate != null && esBkjeonIndexDto.endDate != null) {
            EsQueryDslCreateBuilderUtil.addFilterRangeQuery(
                requestQuery,
                EsBkjeonIndexType.REG_DATE.field,
                esBkjeonIndexDto.startDate,
                esBkjeonIndexDto.endDate
            )
        }

        val request = CountRequest.of { countRequest ->
            countRequest.index(INDEX_NAME)
            countRequest.query { query ->
                query.bool(requestQuery)
            }
        }

        try {
            result = openSearchClient.count(request).count()
        } catch (e: Exception) {
            log.error("getSampleTotalCnt Error!! : $e")
        }

        return result
    }

}