package com.example.ricktestapp.fragments.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsDataItem(
    val imageUrl: String? = null,
    val title: String,
    val details: List<String>,
) : Parcelable