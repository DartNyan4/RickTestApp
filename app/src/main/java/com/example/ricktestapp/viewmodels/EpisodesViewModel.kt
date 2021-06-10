package com.example.ricktestapp.viewmodels

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.ricktestapp.data.EpisodeResponse
import com.example.ricktestapp.di.DaggerAppComponent
import com.example.ricktestapp.repositories.EpisodesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.PublishProcessor
import javax.inject.Inject

class EpisodesViewModel : PagerViewModel() {

    @Inject
    lateinit var repository: EpisodesRepository

    val episodesLiveData by lazy {
        MediatorLiveData<List<EpisodeResponse>>().apply {
            val items = arrayListOf<EpisodeResponse>()
            addSource(newEpisodesLiveData) {
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

    private val newEpisodesLiveData by lazy { MutableLiveData<List<EpisodeResponse>>() }

    init {
        DaggerAppComponent.create().inject(this)
        publishProcessor = initPublishProcessor()
        publishProcessor.onNext(pageNumber)
    }

    override fun initPublishProcessor(): PublishProcessor<Int> {
        return PublishProcessor.create<Int>().also {
            val disposable = it.doOnNext {
                isLoadingLiveData.postValue(true)
            }.concatMapSingle { page ->
                repository.requestEpisodes(page)
            }.observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe({ characters ->
                isLoadingLiveData.postValue(false)
                newEpisodesLiveData.postValue(characters.results)
            },  {
                isLoadingLiveData.postValue(false)
                Log.d(TAG, it.message.toString())
            })
            compositeDisposable.add(disposable)
        }
    }

    companion object {

        private val TAG = EpisodesViewModel::class.simpleName

    }

}