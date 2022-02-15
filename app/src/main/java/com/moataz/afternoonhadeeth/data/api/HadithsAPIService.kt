package com.moataz.afternoonhadeeth.data.api

import com.moataz.afternoonhadeeth.data.model.hadith.Hadith
import io.reactivex.Single
import retrofit2.http.GET

interface HadithsAPIService {
    @GET("hadith.json")
    fun getHadithList(): Single<List<Hadith>>
}