package com.up42.codingchallenge.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@JsonIgnoreProperties(ignoreUnknown = true)
@Document("features")
data class Feature(val properties: Properties?) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Properties(
        @Field("id")
        var id: String?,
        var timestamp: Long?,
        var acquisition: Acquisition? = null,
        var quicklook: String?
    ) {

        @JsonIgnoreProperties(ignoreUnknown = true)
        data class Acquisition(
            var beginViewingDate: Long?,
            var endViewingDate: Long?,
            var missionName: String?
        )
    }
}
