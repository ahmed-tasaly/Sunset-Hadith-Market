package com.moataz.afternoonhadeeth.data.repository

import com.moataz.afternoonhadeeth.data.api.HadithsAPIService
import com.moataz.afternoonhadeeth.data.api.VideosAPIService
import com.moataz.afternoonhadeeth.data.model.hadith.Hadith
import com.moataz.afternoonhadeeth.data.model.videos.top.TopVideosResponse
import com.moataz.afternoonhadeeth.data.request.ApiClint
import io.reactivex.Single

class VideosRepository {
    private val apiServiceVideos: VideosAPIService = ApiClint.apiServiceVideos

    fun executeTopVideosApi(): Single<TopVideosResponse> {
        return apiServiceVideos.getTopVideosList()
    }
}