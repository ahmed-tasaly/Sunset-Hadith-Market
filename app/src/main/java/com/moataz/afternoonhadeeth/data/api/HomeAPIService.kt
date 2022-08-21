package com.moataz.afternoonhadeeth.data.api

import com.moataz.afternoonhadeeth.data.model.home.HomeResponse
import com.moataz.afternoonhadeeth.utils.status.Resource
import kotlinx.coroutines.flow.Flow

interface HomeAPIService {
    suspend fun getHomeListKtor(): Flow<Resource<HomeResponse>>
}