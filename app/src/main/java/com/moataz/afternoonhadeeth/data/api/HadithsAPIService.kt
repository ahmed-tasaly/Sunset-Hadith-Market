package com.moataz.afternoonhadeeth.data.api

import com.moataz.afternoonhadeeth.data.model.hadith.HadithResponse
import com.moataz.afternoonhadeeth.utils.status.Resource
import kotlinx.coroutines.flow.Flow

interface HadithsAPIService {
    suspend fun getHadithsKtor(): Flow<Resource<HadithResponse>>
}