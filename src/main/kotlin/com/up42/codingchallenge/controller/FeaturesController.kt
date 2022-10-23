package com.up42.codingchallenge.controller

import com.up42.codingchallenge.projection.FeatureSummary
import com.up42.codingchallenge.service.FeatureService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Base64
import java.util.UUID

@RestController
@RequestMapping("/features")
class FeaturesController(val featureService: FeatureService) {

    private val logger = LoggerFactory.getLogger(FeaturesController::class.java)

    @GetMapping
    fun getFeatures(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "7") size: Int
    ): Page<FeatureSummary> {
        logger.debug("Received request for feature pagination: page=$page, size=$size")
        return featureService.getFeatures(PageRequest.of(page, size))
    }

    @GetMapping(value = ["/{featureId}"])
    fun getFeature(@PathVariable featureId: UUID): FeatureSummary {
        return featureService.getFeature(featureId)
    }

    @GetMapping(value = ["/{featureId}/quicklook"], produces = ["image/png"])
    fun getFeatureImage(@PathVariable featureId: UUID): ByteArray {
        val quickLookImageContent = this.featureService.getFeatureImage(featureId)
        return Base64.getDecoder().decode(quickLookImageContent.getBase64Image())
    }
}
