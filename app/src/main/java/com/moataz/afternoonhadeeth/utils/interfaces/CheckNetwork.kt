package com.moataz.afternoonhadeeth.utils.interfaces

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log

object CheckNetwork {
    private val TAG = CheckNetwork::class.java.simpleName
    fun isInternetAvailable(context: Context): Boolean {
        val info =
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
        return if (info == null) {
            Log.d(TAG, "no internet connection")
            false
        } else {
            if (info.isConnected) {
                Log.d(TAG, " internet connection available...")
                true
            } else {
                Log.d(TAG, " internet connection")
                true
            }
        }
    }
}