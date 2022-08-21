package com.moataz.afternoonhadeeth.data.api

import com.moataz.afternoonhadeeth.data.model.books.BooksResponse
import com.moataz.afternoonhadeeth.utils.status.Resource
import kotlinx.coroutines.flow.Flow

interface BooksAPIService {
    suspend fun getBooksKtor(): Flow<Resource<BooksResponse>>
}