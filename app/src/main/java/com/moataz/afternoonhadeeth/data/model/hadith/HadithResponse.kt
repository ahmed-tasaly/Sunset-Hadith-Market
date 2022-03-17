package com.moataz.afternoonhadeeth.data.model.hadith

import androidx.annotation.Keep

@Keep
data class HadithResponse(
    val hadithMainData: List<HadithMainData>
)