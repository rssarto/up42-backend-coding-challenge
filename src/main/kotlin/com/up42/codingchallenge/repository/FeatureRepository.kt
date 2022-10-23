package com.up42.codingchallenge.repository

import com.up42.codingchallenge.domain.Feature
import com.up42.codingchallenge.projection.FeatureSummary
import com.up42.codingchallenge.projection.QuicklookImageContent
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface FeatureRepository : PagingAndSortingRepository<Feature, String> {
    @Query(value = "{}", sort = "{ 'properties.timestamp' : 1}")
    fun findFeatureSummaries(pageable: Pageable): Page<FeatureSummary>

    @Query(value = "{ 'properties.id': ?0 }", fields = "{ 'properties.quicklook': 1 }")
    fun getQuicklookImage(id: String): QuicklookImageContent

    @Query(value = "{ 'properties.id': ?0 }")
    fun findFeatureSummaryById(id: String): FeatureSummary?
}
