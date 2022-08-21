package com.moataz.afternoonhadeeth.ui.videos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.afternoonhadeeth.data.model.videos.top.TopVideosResponse
import com.moataz.afternoonhadeeth.data.repository.VideosRepository
import com.moataz.afternoonhadeeth.utils.status.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class VideosTopViewModel : ViewModel() {
    private val videosRepository = VideosRepository()
    private val _onResponse: MutableLiveData<Resource<TopVideosResponse>> = MutableLiveData()
    val onResponse: LiveData<Resource<TopVideosResponse>> = _onResponse

    private fun makeVideosResponseApiCall() {
        viewModelScope.launch {
            val response: Flow<Resource<TopVideosResponse>> = videosRepository.getVideosKtor()
            response.collect {
                _onResponse.postValue(it)
            }
        }
    }

    init {
        makeVideosResponseApiCall()
    }
}