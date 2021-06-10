package com.example.ricktestapp.repositories

import com.example.ricktestapp.data.BaseResponse
import com.example.ricktestapp.data.CharacterResponse
import com.example.ricktestapp.di.DaggerAppComponent
import com.example.ricktestapp.retrofit.RestClient
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CharacterRepository {

    init {
        DaggerAppComponent.create().inject(this)
    }

    fun requestCharacters(page: Int): Single<BaseResponse<CharacterResponse>> {
        return RestClient.instance.getCharacters(page).subscribeOn(Schedulers.io())
    }

}