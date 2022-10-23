package com.up42.codingchallenge.service

import com.up42.codingchallenge.projection.FeatureSummary
import com.up42.codingchallenge.projection.QuicklookImageContent
import java.util.UUID
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FeatureService {

    fun getFeatures(pageable: Pageable): Page<FeatureSummary>
    fun getFeature(id: UUID): FeatureSummary
    fun getFeatureImage(id: UUID): QuicklookImageContent

}
