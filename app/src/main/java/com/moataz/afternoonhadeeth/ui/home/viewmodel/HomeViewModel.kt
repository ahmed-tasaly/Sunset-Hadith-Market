package com.moataz.afternoonhadeeth.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moataz.afternoonhadeeth.data.model.home.HomeResponse
import com.moataz.afternoonhadeeth.data.repository.HomeRepository
import com.moataz.afternoonhadeeth.utils.status.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private val disposables = CompositeDisposable()
    private val mediumObjectsList = MutableLiveData<Resource<HomeResponse>>()
    private val articlesRepository = HomeRepository()

    fun makeApiCallHome(): LiveData<Resource<HomeResponse>> {
        disposables.add(articlesRepository.executeHomeApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result: HomeResponse? -> mediumObjectsList.postValue(Resource.success(result)) }
            ) { mediumObjectsList.postValue(Resource.error("error")) })
        return mediumObjectsList
    }

    override fun onCleared() {
        disposables.clear()
    }
}