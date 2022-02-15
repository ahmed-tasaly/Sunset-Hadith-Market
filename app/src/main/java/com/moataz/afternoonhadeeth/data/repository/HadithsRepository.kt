package com.moataz.afternoonhadeeth.data.repository

import com.moataz.afternoonhadeeth.data.api.HadithsAPIService
import com.moataz.afternoonhadeeth.data.model.hadith.Hadith
import com.moataz.afternoonhadeeth.data.request.ApiClint
import io.reactivex.Single

class HadithsRepository {
    private val apiServiceHadiths: HadithsAPIService = ApiClint.apiServiceHadith

    fun executeHadithsApi(): Single<List<Hadith>> {
        return apiServiceHadiths.getHadithList()
    }
}