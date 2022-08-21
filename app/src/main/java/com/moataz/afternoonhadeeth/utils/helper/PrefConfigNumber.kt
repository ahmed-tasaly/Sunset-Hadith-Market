package com.moataz.afternoonhadeeth.utils.helper

import android.content.Context

object PrefConfigNumber {
    private const val MY_PREFERENCE_NAME = "chosenNumber"
    private const val PREF_TOTAL_KEY = "pref_total_key"

    fun saveChosenNumber(context: Context, number: Int) {
        val pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt(PREF_TOTAL_KEY, number)
        editor.apply()
    }

    fun restoreChosenNumber(context: Context): Int {
        val pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE)
        return pref.getInt(PREF_TOTAL_KEY, 0)
    }
}