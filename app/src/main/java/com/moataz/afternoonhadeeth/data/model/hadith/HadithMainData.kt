package com.moataz.afternoonhadeeth.data.model.hadith

import androidx.annotation.Keep

@Keep
data class HadithMainData(
    val dataList: List<Hadith>,
    var title: String
)