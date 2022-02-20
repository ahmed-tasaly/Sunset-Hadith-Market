package com.moataz.afternoonhadeeth.data.model.home

import androidx.annotation.Keep
import com.moataz.afternoonhadeeth.data.model.home.blocks.Blocks

@Keep
class HomeResponse(
    val videos: List<Videos>,
    val blocks: List<Blocks>,
    val firstHadithChanges: List<HadithChanges>,
    val counter: List<Counter>,
    val secondHadithChanges: List<HadithChanges>,
    val firstTextChanges: List<TextChanges>,
    val thirdHadithChanges: List<HadithChanges>,
    val secondTextChanges: List<TextChanges>,
    val fourthHadithChanges: List<HadithChanges>
)