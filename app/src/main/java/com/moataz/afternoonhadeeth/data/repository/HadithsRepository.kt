package com.moataz.afternoonhadeeth.data.repository

import com.moataz.afternoonhadeeth.data.api.HadithsAPIService
import com.moataz.afternoonhadeeth.data.model.hadith.HadithResponse
import com.moataz.afternoonhadeeth.data.request.ApiClint
import com.moataz.afternoonhadeeth.utils.helper.HttpRoutes
import com.moataz.afternoonhadeeth.utils.status.Resource
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HadithsRepository : HadithsAPIService {
    override suspend fun getHadithsKtor() = flow {
        try {
            emit(Resource.loading())
            val response = ApiClint.client.get<HadithResponse>(HttpRoutes.HADITHS)
            emit(Resource.success(data = response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, msg = e.message ?: "Error Occurred!"))
        }
    }.flowOn(Dispatchers.IO)
}