package com.example.ricktestapp.data

import com.example.ricktestapp.views.models.LocationData
import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") override val name: String,
    @SerializedName("type") override val type: String,
    @SerializedName("dimension") override val dimension: String,
    @SerializedName("residents") val residentsUrls: List<String>,
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String,
) : LocationData