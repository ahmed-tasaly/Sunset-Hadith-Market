package com.moataz.afternoonhadeeth.utils.helper

import android.content.Context
import com.moataz.afternoonhadeeth.utils.interfaces.CheckNetwork
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        lateinit var context: Context
        val cacheControl = CacheControl.Builder()
            .maxAge(1, TimeUnit.DAYS)
            .build()
        return response.newBuilder()
            .removeHeader("Cache-Control")
            .header(
                "Cache-Control",
                if (CheckNetwork.isInternetAvailable(context)) "public, max-age=60" else "public, max-stale=604800"
            ).build()
    }
}