package com.bkjeon.base.core.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.http.HttpHost
import org.apache.http.impl.client.SystemDefaultCredentialsProvider
import org.opensearch.client.RestClient
import org.opensearch.client.json.jackson.JacksonJsonpMapper
import org.opensearch.client.opensearch.OpenSearchClient
import org.opensearch.client.transport.rest_client.RestClientTransport
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenSearchConfiguration {
    @Bean
    fun openSearchClient(): OpenSearchClient {
        val host = HttpHost("localhost", 9200, "http") // OpenSearch Domain
        val restClient = RestClient.builder(host).setHttpClientConfigCallback {
            it.setDefaultCredentialsProvider(SystemDefaultCredentialsProvider())
        }.build()

        return OpenSearchClient(RestClientTransport(restClient, JacksonJsonpMapper(jacksonObjectMapper())))
    }
}