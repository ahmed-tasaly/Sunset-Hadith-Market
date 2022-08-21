package com.moataz.afternoonhadeeth.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.afternoonhadeeth.data.model.home.HomeResponse
import com.moataz.afternoonhadeeth.data.repository.HomeRepository
import com.moataz.afternoonhadeeth.utils.status.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val homeRepository = HomeRepository()
    private val _onResponse: MutableLiveData<Resource<HomeResponse>> = MutableLiveData()
    val onResponse: LiveData<Resource<HomeResponse>> = _onResponse

    private fun makeHomeResponseApiCall() {
        viewModelScope.launch {
            val response: Flow<Resource<HomeResponse>> = homeRepository.getHomeListKtor()
            response.collect {
                _onResponse.postValue(it)
            }
        }
    }

    init {
        makeHomeResponseApiCall()
    }
}