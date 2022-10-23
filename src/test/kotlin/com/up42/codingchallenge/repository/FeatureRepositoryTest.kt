package com.up42.codingchallenge.repository

import com.up42.codingchallenge.base.CustomDataMongoTest
import com.up42.codingchallenge.base.IntegrationTestBase
import java.util.UUID
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable

@CustomDataMongoTest
class FeatureRepositoryTest : IntegrationTestBase() {

    @Autowired
    private lateinit var featureRepository: FeatureRepository

    @Test
    fun `paginate all feature summaries in one page`() {
        val totalElementsInDatabase = featureRepository.count()
        val featureSummaryPage = featureRepository.findFeatureSummaries(Pageable.unpaged())
        assertThat(featureSummaryPage.isEmpty).isEqualTo(false)
        assertThat(featureSummaryPage.totalPages).isEqualTo(1)
        assertThat(featureSummaryPage.totalElements).isEqualTo(totalElementsInDatabase)
        assertThat(featureSummaryPage.size).isEqualTo(totalElementsInDatabase)
    }

    @Test
    fun `paginate feature summaries in two pages`() {
        val totalElementsInDatabase = featureRepository.count()
        assertThat(totalElementsInDatabase).isGreaterThan(1)
        val pageCount = 2
        val pageSize = (totalElementsInDatabase / pageCount) + 1
        val featureSummaryPage = featureRepository.findFeatureSummaries(Pageable.ofSize(pageSize.toInt()))
        assertThat(featureSummaryPage.isEmpty).isEqualTo(false)
        assertThat(featureSummaryPage.totalPages).isEqualTo(pageCount)
        assertThat(featureSummaryPage.totalElements).isEqualTo(totalElementsInDatabase)
        assertThat(featureSummaryPage.size).isEqualTo(pageSize)
    }

    @Test
    fun `find feature by id`() {
        val featureId = UUID.fromString("08a190bf-8c7e-4e94-a22c-7f3be11f642c")
        val featureSummary = featureRepository.findFeatureSummaryById(featureId.toString())
        assertThat(featureSummary).isNotNull
        featureSummary!!.let {
            assertThat(it.getId()).isEqualTo(featureId)
            assertThat(it.getTimestamp()).isEqualTo(1555044772083)
            assertThat(it.getBeginViewingDate()).isEqualTo(1555044772083)
            assertThat(it.getEndViewingDate()).isEqualTo(1555044797082)
            assertThat(it.getMissionName()).isEqualTo("Sentinel-1A")
        }
    }

    @Test
    fun `image should be null when feature has no image`() {
        val featureId = UUID.fromString("4a7eb4f7-7eea-4a2d-9c73-ff9bf177b7e3")
        val featureSummary = featureRepository.findFeatureSummaryById(featureId.toString())
        assertThat(featureSummary).isNotNull
        val quicklookImage = featureRepository.getQuicklookImage(featureId.toString())
        assertThat(quicklookImage).isNotNull
    }

    @Test
    fun `image should be blank when feature image is blank`() {
        val featureId = UUID.fromString("a5775e04-d3b6-4b42-84d6-e74bb715b3a7")
        val featureSummary = featureRepository.findFeatureSummaryById(featureId.toString())
        assertThat(featureSummary).isNotNull
        val quicklookImage = featureRepository.getQuicklookImage(featureId.toString())
        assertThat(quicklookImage.getBase64Image()).isEqualTo("")
    }

    @Test
    fun `feature should be null when id is not found`() {
        val featureId = UUID.fromString("b343e6c2-a40c-4bcc-9afe-69b9fc19dbaa")
        val featureSummary = featureRepository.findFeatureSummaryById(featureId.toString())
        assertThat(featureSummary).isNull()
    }
}
