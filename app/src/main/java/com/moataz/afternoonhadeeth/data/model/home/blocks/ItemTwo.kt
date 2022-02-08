package com.moataz.afternoonhadeeth.data.model.home.blocks

import androidx.annotation.Keep

@Keep
data class ItemTwo(
    var title: String?,
    val dataList: List<DataList>?
)