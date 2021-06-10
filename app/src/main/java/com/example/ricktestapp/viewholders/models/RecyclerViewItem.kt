package com.example.ricktestapp.viewholders.models

/**
 * interface that all recycler view models should implement.
 */
interface RecyclerViewItem: Cloneable {

    // the item type of the model. Each item corresponds to a view type (& View holder)
    val itemType: RecyclerViewItemType

    // must be unique
    val id: Any

    val data: Any

}