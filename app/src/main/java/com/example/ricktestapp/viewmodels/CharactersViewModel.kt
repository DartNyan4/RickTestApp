package com.example.ricktestapp.viewmodels

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.ricktestapp.data.CharacterResponse
import com.example.ricktestapp.di.DaggerAppComponent
import com.example.ricktestapp.repositories.CharacterRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.PublishProcessor
import javax.inject.Inject

class CharactersViewModel: PagerViewModel() {

    @Inject
    lateinit var repository: CharacterRepository

    val charactersLiveData by lazy {
        MediatorLiveData<List<CharacterResponse>>().apply {
            val items = arrayListOf<CharacterResponse>()
            addSource(newCharactersLiveData) {
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

    private val newCharactersLiveData by lazy { MutableLiveData<List<CharacterResponse>>() }

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
                repository.requestCharacters(page)
            }.observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe({ characters ->
                isLoadingLiveData.postValue(false)
                newCharactersLiveData.postValue(characters.results)
            },  {
                isLoadingLiveData.postValue(false)
                Log.d(TAG, it.message.toString())
            })
            compositeDisposable.add(disposable)
        }
    }

    companion object {

        private val TAG = CharactersViewModel::class.simpleName

    }

}