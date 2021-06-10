package com.example.ricktestapp.viewholders.models

import com.example.ricktestapp.data.LocationResponse
import com.example.ricktestapp.fragments.details.DetailsDataItem

class LocationDataModel(
    override val data: LocationResponse
) : RecyclerViewItem {

    override val itemType: RecyclerViewItemType
        get() = RecyclerViewItemType.LOCATION_VIEW

    override val id: Any
        get() = data.id

    fun toDetailsDataItem(): DetailsDataItem {
        return DetailsDataItem(
            title = data.name,
            details = listOf(data.dimension, data.type)
        )
    }

}