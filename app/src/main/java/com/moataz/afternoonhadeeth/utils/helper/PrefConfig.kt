package com.moataz.afternoonhadeeth.utils.helper

import android.content.Context
import android.content.SharedPreferences
import com.moataz.afternoonhadeeth.utils.helper.PrefConfig

object PrefConfig {
    private const val MY_PREFERENCE_NAME = "com.moataz.afternoonhadeeth.utils.helper"
    private const val PREF_TOTAL_KEY = "pref_total_key"

    fun saveTotalInPref(context: Context, total: Int) {
        val pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt(PREF_TOTAL_KEY, total)
        editor.apply()
    }

    fun loadTotalFromPref(context: Context): Int {
        val pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE)
        return pref.getInt(PREF_TOTAL_KEY, 0)
    }
}