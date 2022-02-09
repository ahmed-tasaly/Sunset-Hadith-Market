package com.moataz.afternoonhadeeth.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moataz.afternoonhadeeth.data.model.hadith.Hadith
import com.moataz.afternoonhadeeth.data.repository.HomeRepository
import com.moataz.afternoonhadeeth.utils.status.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HadithViewModel : ViewModel() {
    private val disposables = CompositeDisposable()
    private val imagesObjectsList = MutableLiveData<Resource<List<Hadith>>>()
    private val repository = HomeRepository()

    fun makeApiCallHadith(): LiveData<Resource<List<Hadith>>> {
        disposables.add(repository.executeHadithApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result: List<Hadith>? -> imagesObjectsList.postValue(Resource.success(result)) }
            ) { imagesObjectsList.postValue(Resource.error("error")) })
        return imagesObjectsList
    }

    override fun onCleared() {
        disposables.clear()
    }
}