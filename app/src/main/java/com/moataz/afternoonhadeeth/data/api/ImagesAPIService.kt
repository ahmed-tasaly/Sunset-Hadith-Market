package com.moataz.afternoonhadeeth.data.api

import com.moataz.afternoonhadeeth.data.model.image.Images
import io.reactivex.Single
import retrofit2.http.GET

interface ImagesAPIService {
    @GET("images.json")
    fun getImagesList(): Single<List<Images>>
}