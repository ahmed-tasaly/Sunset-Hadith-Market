package com.moataz.afternoonhadeeth.data.repository

import com.moataz.afternoonhadeeth.data.api.ImagesAPIService
import com.moataz.afternoonhadeeth.data.model.image.Images
import com.moataz.afternoonhadeeth.data.request.ApiClint
import io.reactivex.Single

class ImagesRepository {
    private val apiServiceImages: ImagesAPIService = ApiClint.apiServiceImages

    fun executeImagesApi(): Single<List<Images>> {
        return apiServiceImages.getImagesList()
    }
}