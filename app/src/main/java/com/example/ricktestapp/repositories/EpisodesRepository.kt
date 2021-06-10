package com.example.ricktestapp.repositories

import com.example.ricktestapp.data.BaseResponse
import com.example.ricktestapp.data.EpisodeResponse
import com.example.ricktestapp.di.DaggerAppComponent
import com.example.ricktestapp.retrofit.RestClient
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class EpisodesRepository {

    init {
        DaggerAppComponent.create().inject(this)
    }

    fun requestEpisodes(page: Int): Single<BaseResponse<EpisodeResponse>> {
        return RestClient.instance.getEpisodes(page).subscribeOn(Schedulers.io())
    }

}