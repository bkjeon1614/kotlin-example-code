import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinVersion = "1.9.24"

	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.5"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion

	// MapStruct
	kotlin("kapt") version "1.7.10"
}

group = "com.bkjeon"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	// Base
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// TestCode
	testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
	testImplementation("io.kotest:kotest-assertions-core:5.9.1")
	testImplementation("io.kotest.extensions:kotest-extensions-spring:1.3.0")

	// DB
	runtimeOnly("com.h2database:h2")

	// Swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

	// Cache
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("org.ehcache:ehcache:3.9.5")

	// AWS SDK
	implementation("software.amazon.awssdk:dynamodb-enhanced")
	implementation(platform("software.amazon.awssdk:bom:2.20.56"))

	// OpenSearch
	implementation("org.opensearch.client:opensearch-rest-client:2.13.0")
	implementation("org.opensearch.client:opensearch-java:2.10.0")
	implementation("jakarta.json:jakarta.json-api:2.1.3")

	// MapStruct
	apply(plugin = "kotlin-kapt")
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
	kaptTest("org.mapstruct:mapstruct-processor:1.5.5.Final")

	// Lib
	implementation("com.google.code.gson:gson:2.8.9")
}

configurations.all {
	// Spring 6.0 부터 잠재적인 충돌을 방지하기 위한 라이브러리 예외처리 (예외처리 안할 시 spring-jcl Lib > LogFactoryService 에서 불필요 로그 발생)
	exclude("commons-logging", "commons-logging")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
