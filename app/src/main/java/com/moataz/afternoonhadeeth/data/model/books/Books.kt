package com.moataz.afternoonhadeeth.data.model.books

import androidx.annotation.Keep

@Keep
data class Books(
    val author: String,
    val imageURL: String,
    val title: String,
    val url: String
)