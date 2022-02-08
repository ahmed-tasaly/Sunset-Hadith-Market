package com.moataz.afternoonhadeeth.utils.helper

import android.content.Context
import com.moataz.afternoonhadeeth.utils.interfaces.CheckNetwork
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ForceCacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        lateinit var context: Context
        if (!CheckNetwork.isInternetAvailable(context)) {
            builder.cacheControl(CacheControl.FORCE_CACHE);
        }
        return chain.proceed(builder.build());
    }
}