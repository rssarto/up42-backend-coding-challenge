package com.up42.codingchallenge

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@EnableMongoRepositories
@SpringBootApplication
class BackendCodingChallengeApplication

fun main(args: Array<String>) {
    runApplication<BackendCodingChallengeApplication>(*args)
}
