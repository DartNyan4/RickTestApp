package com.example.ricktestapp.adapters

import android.location.Location
import androidx.recyclerview.widget.RecyclerView
import com.example.ricktestapp.utils.GeneralRecyclerViewAdapter
import com.example.ricktestapp.viewholders.CharacterCardViewHolder
import com.example.ricktestapp.viewholders.EpisodeViewHolder
import com.example.ricktestapp.viewholders.LocationViewHolder
import com.example.ricktestapp.viewholders.models.CharacterDataModel
import com.example.ricktestapp.viewholders.models.EpisodeDataModel
import com.example.ricktestapp.viewholders.models.LocationDataModel

class MainAdapter : GeneralRecyclerViewAdapter() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is CharacterCardViewHolder -> {
                holder.data = getItem(position) as CharacterDataModel
                holder.onViewClicked = { listener?.invoke(getItem(position)) }
            }
            is EpisodeViewHolder -> {
                holder.data = getItem(position) as EpisodeDataModel
                holder.onViewClicked = { listener?.invoke(getItem(position)) }
            }
            is LocationViewHolder -> {
                holder.data = getItem(position) as LocationDataModel
                holder.onViewClicked = { listener?.invoke(getItem(position)) }
            }
        }
    }

}