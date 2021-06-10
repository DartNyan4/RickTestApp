package com.example.ricktestapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor

abstract class PagerViewModel: ViewModel() {

    val shouldReloadLiveData by lazy { MutableLiveData(false) }

    val isLoadingLiveData by lazy { MutableLiveData(false) }

    val isLoading
        get() = isLoadingLiveData.value ?: false

    protected var pageNumber = 1

    protected val compositeDisposable by lazy { CompositeDisposable() }

    protected lateinit var publishProcessor: PublishProcessor<Int>

    abstract fun initPublishProcessor(): PublishProcessor<Int>

    fun onReload() {
        pageNumber = 1
        shouldReloadLiveData.postValue(true)
        compositeDisposable.clear()
        publishProcessor = initPublishProcessor()
        publishProcessor.onNext(pageNumber)
    }

    fun onNextPage() {
        pageNumber++
        publishProcessor.onNext(pageNumber)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}