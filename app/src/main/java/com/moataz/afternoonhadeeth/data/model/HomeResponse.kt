package com.moataz.afternoonhadeeth.data.model

class HomeResponse(
    val firstImage: List<FirstImage>,
    val live: List<Live>,
    val dailyPost: List<DailyPost>,
    val kanzHasanat: List<KanzHasanat>,
    val tahzeebMuslim: List<TahzeebMuslim>,
    val dailyImage: List<DailyImage>,
    val saheehBukhari: List<SaheehBukhari>,
    val saheehMuslim: List<SaheehMuslim>,
)