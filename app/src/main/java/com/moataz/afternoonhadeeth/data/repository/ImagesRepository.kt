package com.moataz.afternoonhadeeth.data.repository

import com.moataz.afternoonhadeeth.data.api.ImagesAPIService
import com.moataz.afternoonhadeeth.data.model.image.Images
import com.moataz.afternoonhadeeth.data.request.ApiClint
import io.reactivex.Single

class ImagesRepository {
    private val apiServiceImages: ImagesAPIService = ApiClint.apiServiceImages

    fun executeImagesWallpaperApi(): Single<List<Images>> {
        return apiServiceImages.getImagesWallpaperList()
    }

    fun executeImagesMasjidApi(): Single<List<Images>> {
        return apiServiceImages.getImagesMasjidList()
    }

    fun executeImagesNabiApi(): Single<List<Images>> {
        return apiServiceImages.getImagesNabiList()
    }

    fun executeImagesKabaApi(): Single<List<Images>> {
        return apiServiceImages.getImagesKabaList()
    }

    fun executeImagesAqsaApi(): Single<List<Images>> {
        return apiServiceImages.getImagesAqsaList()
    }
}