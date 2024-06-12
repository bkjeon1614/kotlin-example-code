package com.bkjeon.base.v1.api.elasticsearch.service

import com.bkjeon.base.feature.entity.elasticsearch.BkjeonIndexAggs
import com.bkjeon.base.feature.repository.elasticsearch.ElasticsearchSampleRepository
import com.bkjeon.base.v1.api.elasticsearch.model.EsRequest
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal class ElasticsearchServiceTest(
    val esSampleRepository: ElasticsearchSampleRepository
): AnnotationSpec() {

    @Test
    fun getSampleAggsList() {
        // Given
        val elasticsearchService = ElasticsearchService(esSampleRepository)

        // When
        val sampleAggsList = elasticsearchService.getSampleAggsList()

        // Then
        sampleAggsList shouldNotBe null
        sampleAggsList.size shouldNotBe 0
    }

    @Test
    fun getSampleList() {
        // Given
        val elasticsearchService = ElasticsearchService(esSampleRepository)

        // When
        val sampleList = elasticsearchService.getSampleList(
            request = EsRequest(
                startDate = null,
                endDate = null,
                title = null,
                author = null,
                age = null,
                ageNosStr = "20"
            )
        )

        // Then
        sampleList.list shouldNotBe null
        sampleList.list.size shouldNotBe 0
    }

    @Test
    fun getSampleTotalCnt() {
        // Given
        val elasticsearchService = ElasticsearchService(esSampleRepository)

        // When
        val sampleTotalCnt = elasticsearchService.getSampleTotalCnt(
            request = EsRequest(
                startDate = null,
                endDate = null,
                title = null,
                author = null,
                age = null,
                ageNosStr = "20"
            )
        )

        // Then
        sampleTotalCnt shouldNotBe 0
    }

}