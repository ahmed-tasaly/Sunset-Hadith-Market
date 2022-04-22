package com.moataz.afternoonhadeeth.data.api

import com.moataz.afternoonhadeeth.data.model.books.BooksResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BooksAPIService {
    @GET("books.json")
    fun getBooksList(): Single<BooksResponse>
}