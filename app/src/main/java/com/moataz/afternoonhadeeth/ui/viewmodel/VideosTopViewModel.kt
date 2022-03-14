package com.moataz.afternoonhadeeth.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moataz.afternoonhadeeth.data.model.videos.top.TopVideosResponse
import com.moataz.afternoonhadeeth.data.repository.VideosRepository
import com.moataz.afternoonhadeeth.utils.status.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class VideosTopViewModel : ViewModel() {
    private val disposables = CompositeDisposable()
    private val topVideosObjectsList = MutableLiveData<Resource<TopVideosResponse>>()
    private val videosRepository = VideosRepository()

    fun makeApiCallTopVideos(): LiveData<Resource<TopVideosResponse>> {
        disposables.add(videosRepository.executeTopVideosApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result: TopVideosResponse? -> topVideosObjectsList.postValue(Resource.success(result)) }
            ) { topVideosObjectsList.postValue(Resource.error("error")) })
        return topVideosObjectsList
    }

    override fun onCleared() {
        disposables.clear()
    }
}