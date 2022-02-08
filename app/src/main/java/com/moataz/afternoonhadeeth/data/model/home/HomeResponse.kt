package com.moataz.afternoonhadeeth.data.model.home

import androidx.annotation.Keep
import com.moataz.afternoonhadeeth.data.model.home.blocks.Blocks

@Keep
class HomeResponse(
    val firstItem: List<FirstItem>,
    val blocks: List<Blocks>,
    val kanzHasanat: List<KanzHasanat>,
    val counter: List<Counter>,
    val tahzeebMuslim: List<TahzeebMuslim>,
    val dailyImage: List<DailyImage>,
    val saheehBukhari: List<SaheehBukhari>,
    val live: List<Live>,
    val saheehMuslim: List<SaheehMuslim>
)