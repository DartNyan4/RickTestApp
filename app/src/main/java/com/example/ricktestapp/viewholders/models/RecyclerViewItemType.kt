package com.example.ricktestapp.viewholders.models

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ricktestapp.viewholders.CharacterCardViewHolder
import com.example.ricktestapp.viewholders.EpisodeViewHolder
import com.example.ricktestapp.viewholders.LocationViewHolder

enum class RecyclerViewItemType(val id: Int) {

    CHARACTER_CARD_VIEW(1),
    EPISODE_VIEW(2),
    LOCATION_VIEW(3);

    companion object {

        private val map = values().associateBy { it.id }

        private fun byId(viewTypeId: Int) = map[viewTypeId] ?: error("View type not found!")

        fun getViewHolderByViewTypeId(parent: ViewGroup, viewTypeId: Int) : RecyclerView.ViewHolder {
            return when(byId(viewTypeId)) {
                CHARACTER_CARD_VIEW -> CharacterCardViewHolder(parent)
                EPISODE_VIEW -> EpisodeViewHolder(parent)
                LOCATION_VIEW -> LocationViewHolder(parent)
            }
        }
    }

}