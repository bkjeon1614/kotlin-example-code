package com.bkjeon.base.v1.schedule

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class AppHealthCheckScheduler {

    @Value("\${spring.config.activate.on-profile:}")
    lateinit var env: String

    companion object {
        private val logger = LoggerFactory.getLogger(AppHealthCheckScheduler::class.java)
        private val httpClient: CloseableHttpClient = HttpClients.createDefault()
    }

    // 10 분에 1번
    @Scheduled(cron = "* 0/10 * * * *")
    fun isAppHealthCheck() {
        val httpGet = HttpGet("https://www.naver.com/")

        try {
            logger.info(">>>>>>>>>>>>>>>>>>>> Health Check: $env")
            httpClient.execute(httpGet)
        } catch (e: Exception) {
            logger.error(">>>>>>>>>>>>>>>>>>>> Error: $e, Env: $env")
        }
    }

}