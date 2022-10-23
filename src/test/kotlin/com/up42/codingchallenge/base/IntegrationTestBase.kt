package com.up42.codingchallenge.base

import com.up42.codingchallenge.repository.FeatureRepositoryTest
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.platform.commons.logging.LoggerFactory
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.MountableFile

@Testcontainers
open class IntegrationTestBase {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(FeatureRepositoryTest::class.java)

        @Container
        val mongoDBContainer = MongoDBContainer("mongo:4.4.2").apply {
            portBindings = listOf("27017:27017")
            start()
        }

        @JvmStatic
        @DynamicPropertySource
        internal fun setProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongo.uri", mongoDBContainer::getReplicaSetUrl)
        }

        @JvmStatic
        @BeforeAll
        internal fun beforeAll() {
            mongoDBContainer.copyFileToContainer(
                MountableFile.forClasspathResource("static/data.json"),
                "/"
            )
            mongoDBContainer.copyFileToContainer(
                MountableFile.forClasspathResource("static/mongodb-preparation-commands.js"),
                "/"
            )
            mongoDBContainer.execInContainer(
                "mongoimport",
                "mongodb://localhost:27017/test",
                "--collection",
                "features",
                "--type",
                "json",
                "--file",
                "/data.json",
                "--jsonArray"
            )
            mongoDBContainer.execInContainer(
                "mongo", "test", "/mongodb-preparation-commands.js"
            )
        }

        @JvmStatic
        @AfterAll
        internal fun afterAll() {
            mongoDBContainer.stop()
        }
    }
}
