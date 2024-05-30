package com.bkjeon.base.v1.api.cache.service

import com.bkjeon.base.core.log.logger
import com.bkjeon.base.feature.domain.data.SampleData
import com.bkjeon.base.v1.api.cache.model.request.CacheSampleRequest
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ModelAttribute

@Service
class CacheSampleService(
    val cacheManager: CacheManager
) {
    private val log = logger()

    @Cacheable(cacheNames = ["getCacheSampleList"])
    fun getCacheSampleList(@ModelAttribute request: CacheSampleRequest): String {
        log.info("EhCache Key List: ${cacheManager.cacheNames}")

        val sampleDataList: MutableList<SampleData> = ArrayList()
        for (i: Int in 1..10000000) {
            sampleDataList.add(SampleData("bkjeon", i, 20_000))
        }
        for (i: Int in 1..10000000) {
            sampleDataList.add(SampleData("bkjeon2", i, 20_000))
        }

        return "OK"
    }

}