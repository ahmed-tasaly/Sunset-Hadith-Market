package com.moataz.afternoonhadeeth.data.api

import com.moataz.afternoonhadeeth.data.model.image.Images
import io.reactivex.Single
import retrofit2.http.GET

interface ImagesAPIService {
    @GET("images.json")
    fun getImagesWallpaperList(): Single<List<Images>>

    @GET("masjidimages.json")
    fun getImagesMasjidList(): Single<List<Images>>

    @GET("nabiimages.json")
    fun getImagesNabiList(): Single<List<Images>>

    @GET("kabaimages.json")
    fun getImagesKabaList(): Single<List<Images>>

    @GET("aqsaimages.json")
    fun getImagesAqsaList(): Single<List<Images>>

}