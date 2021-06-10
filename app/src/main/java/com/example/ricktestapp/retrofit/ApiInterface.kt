package com.example.ricktestapp.retrofit

import com.example.ricktestapp.data.BaseResponse
import com.example.ricktestapp.data.CharacterResponse
import com.example.ricktestapp.data.EpisodeResponse
import com.example.ricktestapp.data.LocationResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("character")
    fun getCharacters(@Query("page") page: Int) : Single<BaseResponse<CharacterResponse>>

    @GET("location")
    fun getLocations(@Query("page") page: Int) : Single<BaseResponse<LocationResponse>>

    @GET("episode")
    fun getEpisodes(@Query("page") page: Int) : Single<BaseResponse<EpisodeResponse>>

}