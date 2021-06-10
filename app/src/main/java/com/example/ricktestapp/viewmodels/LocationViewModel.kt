package com.example.ricktestapp.viewmodels

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.ricktestapp.data.LocationResponse
import com.example.ricktestapp.di.DaggerAppComponent
import com.example.ricktestapp.repositories.LocationRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.PublishProcessor
import javax.inject.Inject

class LocationViewModel : PagerViewModel() {

    @Inject
    lateinit var repository: LocationRepository

    val locationsLiveData by lazy {
        MediatorLiveData<List<LocationResponse>>().apply {
            val items = arrayListOf<LocationResponse>()
            addSource(newLocationsLiveData) {
                items.addAll(it)
                postValue(items)
            }
            addSource(shouldReloadLiveData) { shouldReload ->
                if (shouldReload) {
                    items.clear()
                    shouldReloadLiveData.postValue(false)
                }
            }
        }
    }

    private val newLocationsLiveData by lazy { MutableLiveData<List<LocationResponse>>() }

    init {
        DaggerAppComponent.create().inject(this)
        publishProcessor = initPublishProcessor()
        publishProcessor.onNext(pageNumber)
    }

    override fun initPublishProcessor() : PublishProcessor<Int> {
        return PublishProcessor.create<Int>().also {
            val disposable = it.doOnNext {
                isLoadingLiveData.postValue(true)
            }.concatMapSingle { page ->
                repository.requestLocations(page)
            }.observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe({ characters ->
                isLoadingLiveData.postValue(false)
                newLocationsLiveData.postValue(characters.results)
            },  {
                isLoadingLiveData.postValue(false)
                Log.d(TAG, it.message.toString())
            })
            compositeDisposable.add(disposable)
        }
    }

    companion object {

        private val TAG = LocationViewModel::class.simpleName

    }

}