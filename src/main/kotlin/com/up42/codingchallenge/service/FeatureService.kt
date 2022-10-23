package com.up42.codingchallenge.service

import com.up42.codingchallenge.projection.FeatureSummary
import com.up42.codingchallenge.projection.QuicklookImageContent
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface FeatureService {
    fun getFeatures(pageable: Pageable): Page<FeatureSummary>
    fun getFeature(id: UUID): FeatureSummary
    fun getFeatureImage(id: UUID): QuicklookImageContent
}
