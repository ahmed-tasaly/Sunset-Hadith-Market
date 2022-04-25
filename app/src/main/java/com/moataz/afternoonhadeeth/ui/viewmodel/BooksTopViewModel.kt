package com.moataz.afternoonhadeeth.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moataz.afternoonhadeeth.data.model.books.BooksResponse
import com.moataz.afternoonhadeeth.data.repository.BooksRepository
import com.moataz.afternoonhadeeth.utils.status.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BooksTopViewModel : ViewModel() {
    private val disposables = CompositeDisposable()
    private val booksObjectsList = MutableLiveData<Resource<BooksResponse>>()
    private val repository = BooksRepository()

    fun makeApiCallBooks(): LiveData<Resource<BooksResponse>> {
        disposables.add(repository.executeBooksTopApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result: BooksResponse? -> booksObjectsList.postValue(Resource.success(result)) }
            ) { booksObjectsList.postValue(Resource.error("error")) })
        return booksObjectsList
    }

    override fun onCleared() {
        disposables.clear()
    }
}