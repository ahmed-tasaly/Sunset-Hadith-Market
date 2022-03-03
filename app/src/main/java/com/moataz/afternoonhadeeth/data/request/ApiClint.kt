package com.moataz.afternoonhadeeth.data.request

import com.moataz.afternoonhadeeth.data.api.HadithsAPIService
import com.moataz.afternoonhadeeth.data.api.HomeAPIService
import com.moataz.afternoonhadeeth.data.api.ImagesAPIService
import com.moataz.afternoonhadeeth.data.api.VideosAPIService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiClint {

    private const val BASE_URL = "https://moatazbadawy.github.io/DataHadith/"
    private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    private fun myHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
        return builder.build()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(myHttpClient())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val apiServiceHome: HomeAPIService by lazy {
        retrofit.create(HomeAPIService::class.java)
    }

    val apiServiceVideos: VideosAPIService by lazy {
        retrofit.create(VideosAPIService::class.java)
    }

    val apiServiceHadith: HadithsAPIService by lazy {
        retrofit.create(HadithsAPIService::class.java)
    }

    val apiServiceImages: ImagesAPIService by lazy {
        retrofit.create(ImagesAPIService::class.java)
    }
}