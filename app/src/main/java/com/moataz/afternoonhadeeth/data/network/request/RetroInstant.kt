package com.moataz.afternoonhadeeth.data.network.request

import com.moataz.afternoonhadeeth.data.network.api.APIService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetroInstant {

    private const val BASE_URL = "https://moatazbadawy.github.io/DataHadith/"
    private var retrofit: Retrofit? = null

    fun getRetroInstance(): APIService {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        }
        return retrofit!!.create(APIService::class.java)
    }
}