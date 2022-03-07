package com.moataz.afternoonhadeeth.data.model.videos.top

import androidx.annotation.Keep

@Keep
data class TopVideosResponse(
    val topVideosBlocks: List<TopVideosBlocks>,
    val topVideosList: List<TopVideosList>
)