package com.up42.codingchallenge.projection

import org.springframework.beans.factory.annotation.Value

interface QuicklookImageContent {
    @Value("#{target.properties.quicklook}")
    fun getBase64Image(): String?
}
