package com.bkjeon.base

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@EnableCaching
@SpringBootApplication
class KotlinJpaCodebaseApplication

fun main(args: Array<String>) {
	runApplication<KotlinJpaCodebaseApplication>(*args)
}
