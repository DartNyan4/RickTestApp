package com.example.ricktestapp.viewholders

import android.view.ViewGroup
import com.example.ricktestapp.viewholders.models.LocationDataModel
import com.example.ricktestapp.views.LocationView

class LocationViewHolder(
    parent: ViewGroup
) : BaseViewHolder<LocationView>(LocationView(parent.context, null)) {

    var data: LocationDataModel? = null
        set(value) {
            field = value
            actualView.data = field?.data
        }

}