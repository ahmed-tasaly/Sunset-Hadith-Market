package com.moataz.afternoonhadeeth.data.api

import com.moataz.afternoonhadeeth.data.model.image.Images
import com.moataz.afternoonhadeeth.utils.status.Resource
import kotlinx.coroutines.flow.Flow

interface ImagesAPIService {
    suspend fun getImagesKtor(): Flow<Resource<List<Images>>>
    suspend fun getImagesMasjedKtor(): Flow<Resource<List<Images>>>
    suspend fun getImagesNabawiKtor(): Flow<Resource<List<Images>>>
    suspend fun getImagesKabaKtor(): Flow<Resource<List<Images>>>
    suspend fun getImagesAqsaKtor(): Flow<Resource<List<Images>>>
}