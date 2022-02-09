package com.moataz.afternoonhadeeth.data.repository

import com.moataz.afternoonhadeeth.data.api.APIService
import com.moataz.afternoonhadeeth.data.model.hadith.Hadith
import com.moataz.afternoonhadeeth.data.model.home.HomeResponse
import com.moataz.afternoonhadeeth.data.model.image.Images
import com.moataz.afternoonhadeeth.data.request.ApiClint
import io.reactivex.Single

class ImagesRepository {
    private val apiService: APIService = ApiClint.apiService

    fun executeImagesApi(): Single<List<Images>> {
        return apiService.getImagesList()
    }
}