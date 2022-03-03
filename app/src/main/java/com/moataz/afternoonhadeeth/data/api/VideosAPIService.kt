package com.moataz.afternoonhadeeth.data.api

import com.moataz.afternoonhadeeth.data.model.videos.top.TopVideosResponse
import io.reactivex.Single
import retrofit2.http.GET

interface VideosAPIService {
    @GET("topvideos.json")
    fun getTopVideosList(): Single<TopVideosResponse>
}