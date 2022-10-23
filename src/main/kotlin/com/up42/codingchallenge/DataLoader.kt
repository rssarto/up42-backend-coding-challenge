package com.up42.codingchallenge

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.mongodb.client.model.IndexOptions
import com.mongodb.client.model.Indexes
import com.up42.codingchallenge.domain.Feature
import com.up42.codingchallenge.repository.FeatureRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.core.io.ClassPathResource
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component

@Component
class DataLoader(val featureRepository: FeatureRepository, val mongoTemplate: MongoTemplate) : ApplicationRunner {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(DataLoader::class.java)
    }

    override fun run(args: ApplicationArguments?) {
        mongoTemplate.getCollection("features")
            .createIndex(Indexes.ascending("properties.id"), IndexOptions().unique(true))
        ClassPathResource("/static/data.json").inputStream.bufferedReader().use {
            it.readText().let { jsonString ->
                jacksonObjectMapper().readValue<List<Feature>>(jsonString)
            }.forEach {
                LOGGER.info("$it")
                try {
                    featureRepository.save(it)
                } catch (ignored: Exception) {
                }
            }
        }
    }
}
