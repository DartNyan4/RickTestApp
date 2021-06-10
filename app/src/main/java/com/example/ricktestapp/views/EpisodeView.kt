package com.example.ricktestapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.ricktestapp.R
import com.example.ricktestapp.utils.setDefaultLayoutParams
import com.example.ricktestapp.views.models.EpisodeData
import com.google.android.material.textview.MaterialTextView

class EpisodeView(
    context: Context, attrs: AttributeSet? = null
): LinearLayout(context, attrs) {

    var data: EpisodeData? = null
    set(value) {
        field = value
        titleTextView.text = "${field?.episodeAlias}: ${field?.name}"
        airDateTextView.text = field?.airDate
    }

    private lateinit var titleTextView: MaterialTextView
    private lateinit var airDateTextView: MaterialTextView

    init {
        setDefaultLayoutParams()
        View.inflate(context, R.layout.view_episode, this)
        setUpView()
        findChilds()
    }

    private fun setUpView() {
        orientation = VERTICAL
    }

    private fun findChilds() {
        titleTextView = findViewById(R.id.titleTextView)
        airDateTextView = findViewById(R.id.airDateTextView)
    }

}