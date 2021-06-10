package com.example.ricktestapp.data

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("results") val results: List<T>,
    @SerializedName("info") val info: InfoModel
)

data class InfoModel(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next") val nextUrl: String?,
    @SerializedName("prev") val prevUrl: String?,
)