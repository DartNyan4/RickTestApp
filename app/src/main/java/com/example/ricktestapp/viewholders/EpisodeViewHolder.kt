package com.example.ricktestapp.viewholders

import android.view.ViewGroup
import com.example.ricktestapp.viewholders.models.EpisodeDataModel
import com.example.ricktestapp.views.EpisodeView

class EpisodeViewHolder(
    parent: ViewGroup
) : BaseViewHolder<EpisodeView>(EpisodeView(parent.context, null)) {

    var data: EpisodeDataModel? = null
        set(value) {
            field = value
            actualView.data = field?.data
        }

}