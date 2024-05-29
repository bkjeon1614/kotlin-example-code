package com.bkjeon.base

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinJpaCodebaseApplication

fun main(args: Array<String>) {
	runApplication<KotlinJpaCodebaseApplication>(*args)
}
