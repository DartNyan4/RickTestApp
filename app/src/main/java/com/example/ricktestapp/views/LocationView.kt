package com.example.ricktestapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.ricktestapp.R
import com.example.ricktestapp.utils.setDefaultLayoutParams
import com.example.ricktestapp.views.models.LocationData
import com.google.android.material.textview.MaterialTextView

class LocationView(
    context: Context, attrs: AttributeSet? = null
): LinearLayout(context, attrs) {

    var data: LocationData? = null
        set(value) {
            field = value
            nameTextView.text = field?.name
            typeTextView.text = field?.type
            dimensionTextView.text = field?.dimension
        }

    private lateinit var nameTextView: MaterialTextView
    private lateinit var typeTextView: MaterialTextView
    private lateinit var dimensionTextView: MaterialTextView

    init {
        setDefaultLayoutParams()
        View.inflate(context, R.layout.view_location, this)
        setUpView()
        findChilds()
    }

    private fun setUpView() {
        orientation = VERTICAL
    }

    private fun findChilds() {
        nameTextView = findViewById(R.id.nameTextView)
        typeTextView = findViewById(R.id.typeTextView)
        dimensionTextView = findViewById(R.id.dimensionTextView)
    }

}