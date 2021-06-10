package com.example.ricktestapp.data

import com.google.gson.annotations.SerializedName

data class LinkModel(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)