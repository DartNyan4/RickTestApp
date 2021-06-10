package com.example.ricktestapp.viewholders.models

import com.example.ricktestapp.data.EpisodeResponse
import com.example.ricktestapp.fragments.details.DetailsDataItem

class EpisodeDataModel(
    override val data: EpisodeResponse
) : RecyclerViewItem {

    override val itemType: RecyclerViewItemType
        get() = RecyclerViewItemType.EPISODE_VIEW

    override val id: Any
        get() = data.id

    fun toDetailsDataItem(): DetailsDataItem {
        return DetailsDataItem(
            title = data.name,
            details = listOf(data.episodeAlias, data.airDate)
        )
    }

}