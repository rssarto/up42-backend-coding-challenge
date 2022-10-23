package com.up42.codingchallenge.service

import com.up42.codingchallenge.exception.NotFoundException
import com.up42.codingchallenge.projection.FeatureSummary
import com.up42.codingchallenge.projection.QuicklookImageContent
import com.up42.codingchallenge.repository.FeatureRepository
import java.util.UUID
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@CacheConfig(cacheNames = ["feature"])
class FeatureServiceImpl(val featureRepository: FeatureRepository) : FeatureService {
    private val logger = LoggerFactory.getLogger(FeatureServiceImpl::class.java)

    @Cacheable("featuresPagination")
    override fun getFeatures(pageable: Pageable): Page<FeatureSummary> {
        logger.debug("Init getFeatures...")
        return featureRepository.findFeatureSummaries(pageable)
    }

    @Cacheable(value = ["feature"], key = "#id")
    override fun getFeature(id: UUID): FeatureSummary = this.featureRepository.findFeatureSummaryById(id.toString())
        ?: throw NotFoundException("No feature found with id $id")

    @Cacheable(value = ["feature_image"], key = "#id")
    override fun getFeatureImage(id: UUID): QuicklookImageContent {
        logger.debug("Init getFeatureImage...")
        val featureSummary = getFeature(id)
        logger.debug("Feature id $id was found...")
        val quicklookImageContent = featureRepository.getQuicklookImage(id.toString())
        if (quicklookImageContent.getBase64Image() == null || quicklookImageContent.getBase64Image()!!.isBlank()) {
            logger.warn("Feature id $id has no associated image...")
            throw NotFoundException("No image content found for id ${featureSummary.getId()}")
        }
        logger.debug("Feature image was found for feature id $id")
        return quicklookImageContent
    }
}
