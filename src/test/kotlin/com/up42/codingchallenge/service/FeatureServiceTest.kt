package com.up42.codingchallenge.service

import com.up42.codingchallenge.base.IntegrationTestBase
import com.up42.codingchallenge.exception.NotFoundException
import java.util.UUID
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FeatureServiceTest : IntegrationTestBase() {

    @Autowired
    private lateinit var featureService: FeatureService

    @Test
    fun `should throw exception when feature is not found()`() {
        val featureId = UUID.fromString("b343e6c2-a40c-4bcc-9afe-69b9fc19dbaa")
        val thrownException = Assertions.assertThrows(
            NotFoundException::class.java,
            { featureService.getFeatureImage(featureId) },
            "Expected getFeatureImage to throw, but it didn't."
        )
        assertThat(thrownException.message)
            .isEqualTo("No feature found with id $featureId")
    }

    @Test
    fun `should throw exception when feature has no image`() {
        val featureId = UUID.fromString("4a7eb4f7-7eea-4a2d-9c73-ff9bf177b7e3")
        val thrownException = Assertions.assertThrows(
            NotFoundException::class.java,
            { featureService.getFeatureImage(featureId) },
            "Expected getFeatureImage to throw, but it didn't."
        )
        assertThat(thrownException.message)
            .isEqualTo("No image content found for id $featureId")
    }

    @Test
    fun `should throw exception when feature image is blank`() {
        val featureId = UUID.fromString("a5775e04-d3b6-4b42-84d6-e74bb715b3a7")
        val thrownException = Assertions.assertThrows(
            NotFoundException::class.java,
            { featureService.getFeatureImage(featureId) },
            "Expected getFeatureImage to throw, but it didn't."
        )
        assertThat(thrownException.message)
            .isEqualTo("No image content found for id $featureId")
    }
}