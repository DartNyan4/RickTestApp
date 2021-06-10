package com.example.ricktestapp.data

import com.example.ricktestapp.views.models.CharacterData
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") override val name: String,
    @SerializedName("status") override val status: String,
    @SerializedName("species") override val species: String,
    @SerializedName("type") val type: String,
    @SerializedName("gender") override val gender: String,
    @SerializedName("image") override val imageUrl: String,
    @SerializedName("created") val created: String,
    @SerializedName("origin") val origin: LinkModel,
    @SerializedName("location") val location: LinkModel,
    @SerializedName("episode") val episodes: List<String>,
) : CharacterData