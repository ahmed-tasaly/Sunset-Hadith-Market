package com.moataz.afternoonhadeeth.data.repository

import com.moataz.afternoonhadeeth.data.api.ImagesAPIService
import com.moataz.afternoonhadeeth.data.model.image.Images
import com.moataz.afternoonhadeeth.data.request.ApiClint
import com.moataz.afternoonhadeeth.utils.helper.HttpRoutes
import com.moataz.afternoonhadeeth.utils.status.Resource
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ImagesRepository : ImagesAPIService {
    override suspend fun getImagesKtor() = flow {
        try {
            emit(Resource.loading())
            val response = ApiClint.client.get<List<Images>>(HttpRoutes.IMAGES)
            emit(Resource.success(data = response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, msg = e.message ?: "Error Occurred!"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getImagesMasjedKtor() = flow {
        try {
            emit(Resource.loading())
            val response = ApiClint.client.get<List<Images>>(HttpRoutes.MASJED_IMAGES)
            emit(Resource.success(data = response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, msg = e.message ?: "Error Occurred!"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getImagesNabawiKtor() = flow {
        try {
            emit(Resource.loading())
            val response = ApiClint.client.get<List<Images>>(HttpRoutes.NABAWI_IMAGES)
            emit(Resource.success(data = response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, msg = e.message ?: "Error Occurred!"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getImagesKabaKtor() = flow {
        try {
            emit(Resource.loading())
            val response = ApiClint.client.get<List<Images>>(HttpRoutes.KABA_IMAGES)
            emit(Resource.success(data = response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, msg = e.message ?: "Error Occurred!"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getImagesAqsaKtor() = flow {
        try {
            emit(Resource.loading())
            val response = ApiClint.client.get<List<Images>>(HttpRoutes.AQSA_IMAGES)
            emit(Resource.success(data = response))
        } catch (e: Exception) {
            emit(Resource.error(data = null, msg = e.message ?: "Error Occurred!"))
        }
    }.flowOn(Dispatchers.IO)
}