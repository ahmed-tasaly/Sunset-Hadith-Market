package com.moataz.afternoonhadeeth.data.repository

import com.moataz.afternoonhadeeth.data.api.HomeAPIService
import com.moataz.afternoonhadeeth.data.model.home.HomeResponse
import com.moataz.afternoonhadeeth.data.request.ApiClint.client
import com.moataz.afternoonhadeeth.utils.helper.HttpRoutes
import com.moataz.afternoonhadeeth.utils.status.Resource
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeRepository : HomeAPIService {
    override suspend fun getHomeListKtor() = flow {
        try {
            emit(Resource.loading())
            val response = client.get<HomeResponse>(HttpRoutes.HOME)
            emit(Resource.success(data = response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, msg = e.message ?: "Error Occurred!"))
        }
    }.flowOn(Dispatchers.IO)
}