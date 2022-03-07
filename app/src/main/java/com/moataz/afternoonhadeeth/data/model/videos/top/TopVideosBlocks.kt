package com.moataz.afternoonhadeeth.data.model.videos.top

import androidx.annotation.Keep

@Keep
data class TopVideosBlocks(
    val itemFour: ItemFour?,
    val itemOne: ItemOne?,
    val itemThree: ItemThree?,
    val itemTwo: ItemTwo?
)