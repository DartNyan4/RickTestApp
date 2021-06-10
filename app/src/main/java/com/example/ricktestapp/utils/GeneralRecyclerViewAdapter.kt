package com.example.ricktestapp.utils

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ricktestapp.viewholders.models.RecyclerViewItem
import com.example.ricktestapp.viewholders.models.RecyclerItemData
import com.example.ricktestapp.viewholders.models.RecyclerViewItemType

abstract class GeneralRecyclerViewAdapter :
    ListAdapter<RecyclerViewItem, RecyclerView.ViewHolder>(ITEMS_COMPARATOR) {

    open var listener: ((RecyclerViewItem) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        return getItem(position).itemType.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecyclerViewItemType.getViewHolderByViewTypeId(parent, viewType)
    }

    companion object {

        private val ITEMS_COMPARATOR = object : DiffUtil.ItemCallback<RecyclerViewItem>() {

            override fun areItemsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem): Boolean {
                val oldData = oldItem.data
                val newData = newItem.data
                return if ((oldData is RecyclerItemData) && (newData is RecyclerItemData)) {
                    oldData.isSameData(newData)
                } else {
                    oldData == newData
                }
            }

        }

    }

}