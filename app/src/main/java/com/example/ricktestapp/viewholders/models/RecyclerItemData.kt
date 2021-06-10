package com.example.ricktestapp.viewholders.models

interface RecyclerItemData {

    fun isSameData(data: RecyclerItemData) : Boolean {
        return this == data
    }

}