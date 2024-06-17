package com.bkjeon.base.v1.api.elasticsearch.service

import com.bkjeon.base.feature.repository.elasticsearch.ElasticsearchSampleRepository
import com.bkjeon.base.v1.api.elasticsearch.model.EsRequest
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal class ElasticsearchServiceTest(
    val esSampleRepository: ElasticsearchSampleRepository
): BehaviorSpec({

    given("ElasticsearchSampleRepository 가 있고") {
        val elasticsearchService = ElasticsearchService(esSampleRepository)

        When("getSampleAggsList 를 불러오면") {
            val sampleAggsList = elasticsearchService.getSampleAggsList()

            then("null 값이 아니고, 0 보다 크다.") {
                sampleAggsList shouldNotBe null
                sampleAggsList.size shouldNotBe 0
            }
        }

        When("getSampleList 를 불러오면") {
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

            then("null 값이 아니고, 0 보다 크다.") {
                sampleList.list shouldNotBe null
                sampleList.list.size shouldNotBe 0
            }
        }

        When("getSampleTotalCnt 를 불러오면") {
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

            then("sampleTotalCnt should not be 0") {
                sampleTotalCnt shouldNotBe 0
            }
        }
    }

})