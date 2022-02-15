package com.moataz.afternoonhadeeth.data.repository

import com.moataz.afternoonhadeeth.data.api.HomeAPIService
import com.moataz.afternoonhadeeth.data.model.home.HomeResponse
import com.moataz.afternoonhadeeth.data.request.ApiClint
import io.reactivex.Single

class HomeRepository {
    private val apiServiceHome: HomeAPIService = ApiClint.apiServiceHome

    fun executeHomeApi(): Single<HomeResponse> {
        return apiServiceHome.getHomeList()
    }
}