package com.moataz.afternoonhadeeth.data.model.videos.top

data class TopVideosResponse(
    val topVideosBlocks: List<TopVideosBlocks>,
    val topVideosList: List<TopVideosList>
)