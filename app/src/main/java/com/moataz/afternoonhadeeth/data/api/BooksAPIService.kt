package com.moataz.afternoonhadeeth.data.api

import com.moataz.afternoonhadeeth.data.model.books.BooksResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BooksAPIService {
    @GET("books.json")
    fun getBooksTopList(): Single<BooksResponse>

    @GET("booksserah.json")
    fun getBooksSerahList(): Single<BooksResponse>

    @GET("bookshadith.json")
    fun getBooksHadithList(): Single<BooksResponse>
}