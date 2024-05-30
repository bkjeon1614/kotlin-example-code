package com.bkjeon.base.v1.api.data.controller

import com.bkjeon.base.feature.domain.data.SampleData
import com.bkjeon.base.core.log.logger
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Stream API", description = "Stream 처리 관련 API")
@RestController
@RequestMapping("/data/stream")
class StreamController {
    private val log = logger()

    @Operation(summary = "arrayList to 연산", description = "ArrayList 의 특정 항목을 연산")
    @GetMapping("/arrayListToCalculate")
    fun getArrayListToCalculate(): Unit {
        val sampleDataList: List<SampleData> = listOf(
            SampleData("bkjeon", 10, 20_000),
            SampleData("bkjeon2", 20, 1_000),
            SampleData("bkjeon3", 30, 10)
        )
        val totalCount: Int = sampleDataList.sumOf { it.col2 }  // 60
        val totalPrice: Int = sampleDataList.sumOf { it.col2 * it.col3 }    // 220300
        val minPrice: Int = sampleDataList.minOf { it.col3 }    // 10
        val maxCount: Int = sampleDataList.maxOf { it.col2 }    // 30
        log.info("totalCount: $totalCount")
        log.info("totalPrice: $totalPrice")
        log.info("minPrice: $minPrice")
        log.info("maxCount: $maxCount")
    }

    @Operation(summary = "arrayList to listString", description = "ArrayList 를 특정 항목의 List<String> 로 변환")
    /*
    @Parameters(
        Parameter(name = "", description = "", required = true)
    )
     */
    @GetMapping("/arrayListToListString")
    fun getArrayListToListString(): List<String> {
        val sampleDataList: List<SampleData> = listOf(
            SampleData("bkjeon", 10, 20_000),
            SampleData("bkjeon2", 20, 1_000),
            SampleData("bkjeon3", 30, 10)
        )
        return sampleDataList.map { it.col1.toString() }
    }

    @Operation(summary = "arrayList to map", description = "ArrayList 를 Map 으로 변환")
    @GetMapping("/arrayListToMap")
    fun getArrayListToMap(): Map<String?, Int?> {
        val sampleDataList: List<SampleData> = listOf(
            SampleData("bkjeon", 10, 20_000),
            SampleData("bkjeon2", 20, 1_000),
            SampleData("bkjeon3", 30, 10),
            SampleData("bkjeon3", 40, 20)
        )
        // associate 는 key 값이 중복되면 마지막 들어온 값이 map 에 추가된다.
        return sampleDataList.associate { it.col1 to it.col2 }
    }

}