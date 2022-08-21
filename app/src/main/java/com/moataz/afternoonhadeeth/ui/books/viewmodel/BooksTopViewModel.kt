package com.moataz.afternoonhadeeth.ui.books.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.afternoonhadeeth.data.model.books.BooksResponse
import com.moataz.afternoonhadeeth.data.repository.BooksRepository
import com.moataz.afternoonhadeeth.utils.status.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BooksTopViewModel : ViewModel() {
    private val repository = BooksRepository()
    private val _onResponse: MutableLiveData<Resource<BooksResponse>> = MutableLiveData()
    val onResponse: LiveData<Resource<BooksResponse>> = _onResponse

    private fun makeBooksResponseApiCall() {
        viewModelScope.launch {
            val response: Flow<Resource<BooksResponse>> = repository.getBooksKtor()
            response.collect {
                _onResponse.postValue(it)
            }
        }
    }

    init {
        makeBooksResponseApiCall()
    }
}