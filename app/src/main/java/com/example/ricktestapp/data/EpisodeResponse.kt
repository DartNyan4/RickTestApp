package com.example.ricktestapp.data

import com.example.ricktestapp.views.models.EpisodeData
import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") override val name: String,
    @SerializedName("air_date") override val airDate: String,
    @SerializedName("episode") override val episodeAlias: String,
    @SerializedName("characters") val charactersUrls: List<String>,
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String,
) : EpisodeData