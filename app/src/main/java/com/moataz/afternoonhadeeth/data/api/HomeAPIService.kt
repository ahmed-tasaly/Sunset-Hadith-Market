package com.moataz.afternoonhadeeth.data.api

import com.moataz.afternoonhadeeth.data.model.home.HomeResponse
import io.reactivex.Single
import retrofit2.http.GET

interface HomeAPIService {
    @GET("homepagedata.json")
    fun getHomeList(): Single<HomeResponse>
}