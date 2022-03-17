package com.moataz.afternoonhadeeth.data.api

import com.moataz.afternoonhadeeth.data.model.hadith.HadithResponse
import io.reactivex.Single
import retrofit2.http.GET

interface HadithsAPIService {
    @GET("hadithlist.json")
    fun getHadithList(): Single<HadithResponse>
}