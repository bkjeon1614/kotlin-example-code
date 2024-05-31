Kotlin Example Code
=========

## 1. Development Environment
[kotlin-example-code]
* IDE: IntelliJ IDEA Ultimate
* SpringBoot: 3.2.5
* Java: 21
* Kotlin: 1.9.24
* Gradle: 8.7
* MacOS: Monterey
* Docker: 20.10.17
  * Docker Compose: v2.6.1
  * Elasticsearch: 7.9.2
  * Kibana: 7.9.2
  * Cerebro: Latest
  * MySQL: 8.0

## 2. IDE Setting
- **scripts -> install.sh 실행 후 로컬 실행시 필요 프로그램들 설치 (필수)**
- File -> Settings.. -> Editor -> File Encoding -> "Project Encoding": UTF-8, "Default encoding for properties files": UTF-8
- AWS Connection Setting (AWS CLI 에서 등록하는것이 편함)
    - 우측 하단 `AWS: default@~~` 클릭 > Edit AWS Credential file(s)
    - 발급 받은 계정 정보 입력
      ```
      #test
      [tst]
      region = ap-northeast-2
      output = json
      aws_access_key_id = {aws_access_key_id}
      aws_secret_access_key = {aws_secret_access_key}
      ```

## 3. Info
- Application: http://localhost:9090
- Swagger: http://localhost:9090/swagger-ui/index.html
- H2 Console: http://localhost:9090/h2-console
- Elasticsearch: http://localhost:9200
- Kibana: http://localhost:5601
- Cerebro: http://localhost:9000