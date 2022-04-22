package com.moataz.afternoonhadeeth.data.model.books

import androidx.annotation.Keep

@Keep
data class BooksResponse(
    val booksList: List<Books>
)