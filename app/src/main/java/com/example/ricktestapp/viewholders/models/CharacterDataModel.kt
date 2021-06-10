package com.example.ricktestapp.viewholders.models

import com.example.ricktestapp.data.CharacterResponse
import com.example.ricktestapp.fragments.details.DetailsDataItem

class CharacterDataModel(
    override val data: CharacterResponse
) : RecyclerViewItem {

    override val itemType: RecyclerViewItemType
        get() = RecyclerViewItemType.CHARACTER_CARD_VIEW

    override val id: Any
        get() = data.id

    fun toDetailsDataItem(): DetailsDataItem {
        return DetailsDataItem(
            imageUrl = data.imageUrl,
            title = data.name,
            details = listOf(data.species, data.status, data.gender, data.location.name)
        )
    }

}