package com.moataz.afternoonhadeeth.data.request

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.cache.*
import io.ktor.client.features.json.*

object ApiClint {
    val client = HttpClient {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 10000
            connectTimeoutMillis = 10000
        }
        install(HttpCache)
    }
}