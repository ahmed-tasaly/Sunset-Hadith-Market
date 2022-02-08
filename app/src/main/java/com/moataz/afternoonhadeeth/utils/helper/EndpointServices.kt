package com.moataz.afternoonhadeeth.utils.helper

import android.content.Context
import android.util.Log
import okhttp3.*
import okhttp3.internal.cache.CacheStrategy
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class EndpointServices {

    companion object {

        fun interceptor(): Interceptor {
            return Interceptor { chain ->
                val request: Request = chain.request()
                val originalResponse: Response = chain.proceed(request)
                val cacheControlStatus: String? = originalResponse.header("Cache-Control")
                if (cacheControlStatus == null || cacheControlStatus.contains("no-store") || cacheControlStatus.contains(
                        "no-cache") ||
                    cacheControlStatus.contains("must-revalidate") || cacheControlStatus.contains("max-stale=0")
                ) {

                    Log.wtf("INTERCEPT", "ORIGINAL CACHE-CONTROL: $cacheControlStatus")

                } else {

                    Log.wtf("INTERCEPT",
                        "ORIGINAL : CACHE-CONTROL: $cacheControlStatus")

                }

                Log.wtf("INTERCEPT",
                    "OVERWRITE CACHE-CONTROL: ${request.cacheControl} | CACHEABLE? ${
                        CacheStrategy.isCacheable(originalResponse,
                            request)
                    }")

                originalResponse.newBuilder()
                    .build()

            }
        }


        fun onlineOfflineHandling(): Interceptor {
            return Interceptor { chain ->
                try {
                    Log.wtf("INTERCEPT", "FETCH ONLINE")
                    val cacheControl = CacheControl.Builder()
                        .maxAge(5, TimeUnit.SECONDS)
                        .build()

                    val response = chain.proceed(chain.request().newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, $cacheControl")
                        .build())

                    Log.wtf("INTERCEPT", "CACHE ${response.cacheResponse} NETWORK ${response.networkResponse}")

                    response
                } catch (e: Exception) {
                    Log.wtf("INTERCEPT", "FALLBACK TO CACHE ${e.message}")

                    val cacheControl: CacheControl = CacheControl.Builder()
                        .maxStale(1, TimeUnit.DAYS)
                        .onlyIfCached() // Use Cache if available
                        .build()

                    val offlineRequest: Request = chain.request().newBuilder()
                        .cacheControl(cacheControl)
                        .build()

                    val response = chain.proceed(offlineRequest)

                    Log.wtf("INTERCEPT", "CACHE ${response.cacheResponse} NETWORK ${response.networkResponse}")

                    response
                }
            }
        }


        fun create(baseUrl: String, context: Context): EndpointServices {

            // Inexact 150 MB of maximum cache size for a total of 4000 assets where about 1MB/30 assets
            // The remaining available space will be use for other cacheable requests
            val cacheSize: Long = 150 * 1024 * 1024

            val cache = Cache(context.cacheDir, cacheSize)

            Log.wtf("CACHE DIRECTORY", cache.directory.absolutePath)

            for (cacheUrl in cache.urls())
                Log.wtf("CACHE URLS", cacheUrl)

            Log.wtf("CACHE OCCUPIED/TOTAL SIZE", "${cache.size()} ${cache.maxSize()}")

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interceptor)
                .callTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor())
                .addInterceptor(onlineOfflineHandling())
                .build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    MoshiConverterFactory.create()
                )
                .client(httpClient)
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(EndpointServices::class.java)

        }

    }
}