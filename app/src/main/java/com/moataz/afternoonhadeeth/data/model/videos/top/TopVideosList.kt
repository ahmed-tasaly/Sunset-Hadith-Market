package com.moataz.afternoonhadeeth.data.model.videos.top

import androidx.annotation.Keep

@Keep
data class TopVideosList(
    val details: String?,
    val image: String?,
    val text: String?,
    val times: String?,
    val url: String?
)