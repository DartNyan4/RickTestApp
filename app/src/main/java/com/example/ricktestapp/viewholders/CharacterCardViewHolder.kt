package com.example.ricktestapp.viewholders

import android.view.ViewGroup
import com.example.ricktestapp.R
import com.example.ricktestapp.utils.setSideMargin
import com.example.ricktestapp.utils.setVerticalMargin
import com.example.ricktestapp.viewholders.models.CharacterDataModel
import com.example.ricktestapp.views.CharacterCardView

class CharacterCardViewHolder(
    parent: ViewGroup
) : BaseViewHolder<CharacterCardView>(CharacterCardView(parent.context, null)) {

    init {
        actualView.setSideMargin(R.dimen.defaultSideMargin)
        actualView.setVerticalMargin(R.dimen.cardVerticalMargin)
    }

    var data: CharacterDataModel? = null
    set(value) {
        field = value
        actualView.data = field?.data
    }

}