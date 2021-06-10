package com.example.ricktestapp.repositories

import com.example.ricktestapp.data.BaseResponse
import com.example.ricktestapp.data.LocationResponse
import com.example.ricktestapp.di.DaggerAppComponent
import com.example.ricktestapp.retrofit.RestClient
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class LocationRepository {

    init {
        DaggerAppComponent.create().inject(this)
    }

    fun requestLocations(page: Int): Single<BaseResponse<LocationResponse>> {
        return RestClient.instance.getLocations(page).subscribeOn(Schedulers.io())
    }

}