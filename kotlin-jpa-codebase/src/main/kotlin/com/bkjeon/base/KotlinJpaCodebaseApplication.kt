package com.bkjeon.base

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class KotlinJpaCodebaseApplication

fun main(args: Array<String>) {
	runApplication<KotlinJpaCodebaseApplication>(*args)
}
