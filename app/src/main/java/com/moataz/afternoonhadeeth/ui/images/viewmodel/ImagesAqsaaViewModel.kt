package com.moataz.afternoonhadeeth.ui.images.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.afternoonhadeeth.data.model.image.Images
import com.moataz.afternoonhadeeth.data.repository.ImagesRepository
import com.moataz.afternoonhadeeth.utils.status.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ImagesAqsaaViewModel : ViewModel() {
    private val repository = ImagesRepository()
    private val _onResponse: MutableLiveData<Resource<List<Images>>> = MutableLiveData()
    val onResponse: LiveData<Resource<List<Images>>> = _onResponse

    private fun makeImagesResponseApiCall() {
        viewModelScope.launch {
            val response: Flow<Resource<List<Images>>> = repository.getImagesAqsaKtor()
            response.collect {
                _onResponse.postValue(it)
            }
        }
    }

    init {
        makeImagesResponseApiCall()
    }
}