package com.moataz.afternoonhadeeth.data.repository

import com.moataz.afternoonhadeeth.data.api.BooksAPIService
import com.moataz.afternoonhadeeth.data.api.HadithsAPIService
import com.moataz.afternoonhadeeth.data.model.books.BooksResponse
import com.moataz.afternoonhadeeth.data.model.hadith.HadithResponse
import com.moataz.afternoonhadeeth.data.request.ApiClint
import io.reactivex.Single

class BooksRepository {
    private val apiServiceBooks: BooksAPIService = ApiClint.apiServiceBooks

    fun executeBooksApi(): Single<BooksResponse> {
        return apiServiceBooks.getBooksList()
    }
}