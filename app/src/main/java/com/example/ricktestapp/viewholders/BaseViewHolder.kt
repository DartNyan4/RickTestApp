package com.example.ricktestapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T : View>(itemView: T): RecyclerView.ViewHolder(itemView) {

    @Suppress("UNCHECKED_CAST")
    val actualView: T
        get() {
            return itemView as T
        }

    open var onViewClicked: (() -> Unit)? = null
        set(value) {
            field = value
            actualView.setOnClickListener {
                onViewClicked?.invoke()
            }
        }

}