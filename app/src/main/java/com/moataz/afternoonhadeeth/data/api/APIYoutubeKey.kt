package com.moataz.afternoonhadeeth.data.api

import android.util.Base64

object APIYoutubeKey {
    private const val API_KEY_PART_1 = "AIzaSyC8nxgx"
    private const val API_KEY_PART_2 = "ZihbcaUUn"
    private const val API_KEY_PART_3 = "NQmzGv7k"
    private const val API_KEY_PART_4 = "BeH9PGU-0g"
    private const val myEncodedApiKey =
        com.moataz.afternoonhadeeth.data.api.APIYoutubeKey.API_KEY_PART_1 + com.moataz.afternoonhadeeth.data.api.APIYoutubeKey.API_KEY_PART_2 + com.moataz.afternoonhadeeth.data.api.APIYoutubeKey.API_KEY_PART_3 + com.moataz.afternoonhadeeth.data.api.APIYoutubeKey.API_KEY_PART_4

    private val decodedApiKey = Base64.decode(
        com.moataz.afternoonhadeeth.data.api.APIYoutubeKey.myEncodedApiKey,
        Base64.DEFAULT
    )
    val API_YOUTUBE_KEY = String(com.moataz.afternoonhadeeth.data.api.APIYoutubeKey.decodedApiKey)
}