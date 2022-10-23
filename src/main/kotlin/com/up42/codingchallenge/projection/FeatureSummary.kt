package com.up42.codingchallenge.projection

import java.util.UUID
import org.springframework.beans.factory.annotation.Value

interface FeatureSummary {

    @Value("#{target.properties.id}")
    fun getId(): UUID?

    @Value("#{target.properties.timestamp}")
    fun getTimestamp(): Long?

    @Value("#{target.properties.acquisition.beginViewingDate}")
    fun getBeginViewingDate(): Long?

    @Value("#{target.properties.acquisition.endViewingDate}")
    fun getEndViewingDate(): Long?

    @Value("#{target.properties.acquisition.missionName}")
    fun getMissionName(): String?
}
