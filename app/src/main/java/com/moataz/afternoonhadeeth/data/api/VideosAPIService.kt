package com.moataz.afternoonhadeeth.data.api

import com.moataz.afternoonhadeeth.data.model.videos.top.TopVideosResponse
import com.moataz.afternoonhadeeth.utils.status.Resource
import kotlinx.coroutines.flow.Flow

interface VideosAPIService {
    suspend fun getVideosKtor(): Flow<Resource<TopVideosResponse>>
}