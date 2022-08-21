package com.moataz.afternoonhadeeth.ui.hadiths.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.afternoonhadeeth.data.model.hadith.HadithResponse
import com.moataz.afternoonhadeeth.data.repository.HadithsRepository
import com.moataz.afternoonhadeeth.utils.status.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HadithViewModel : ViewModel() {
    private val hadithsRepository = HadithsRepository()
    private val _onResponse: MutableLiveData<Resource<HadithResponse>> = MutableLiveData()
    val onResponse: LiveData<Resource<HadithResponse>> = _onResponse

    private fun makeHadithsResponseApiCall() {
        viewModelScope.launch {
            val response: Flow<Resource<HadithResponse>> = hadithsRepository.getHadithsKtor()
            response.collect {
                _onResponse.postValue(it)
            }
        }
    }

    init {
        makeHadithsResponseApiCall()
    }
}