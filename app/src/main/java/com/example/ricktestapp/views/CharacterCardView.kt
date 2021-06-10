package com.example.ricktestapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.ricktestapp.R
import com.example.ricktestapp.utils.loadUrl
import com.example.ricktestapp.utils.setDefaultLayoutParams
import com.example.ricktestapp.views.models.CharacterData
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import org.jetbrains.anko.dimen

class CharacterCardView(
    context: Context, attrs: AttributeSet? = null
): MaterialCardView(context, attrs) {

    var data: CharacterData? = null
    set(value) {
        field = value
        infoLayout.isVisible = field != null
        field?.let { data ->
            nameTextView.text = field?.name
            speciesTextView.text = context.getString(R.string.species_format, data.species)
            statusTextView.text = context.getString(R.string.status_format, data.status)
            genderTextView.text = context.getString(R.string.gender_format, data.gender)
            imageView.loadUrl(field?.imageUrl)
        }
    }
    private lateinit var infoLayout: LinearLayout
    private lateinit var imageView: ImageView
    private lateinit var nameTextView: MaterialTextView
    private lateinit var speciesTextView: MaterialTextView
    private lateinit var statusTextView: MaterialTextView
    private lateinit var genderTextView: MaterialTextView

    init {
        setDefaultLayoutParams()
        View.inflate(context, R.layout.view_character_card, this)
        setUpView()
        findChilds()
    }

    private fun setUpView() {
        radius = dimen(R.dimen.defaultCardRadius).toFloat()
        strokeWidth = dimen(R.dimen.defaultCardStrokeWidth)
        strokeColor = ContextCompat.getColor(context, R.color.darkGreen)
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
    }

    private fun findChilds() {
        infoLayout = findViewById(R.id.infoLayout)
        imageView = findViewById(R.id.imageView)
        nameTextView = findViewById(R.id.nameTextView)
        speciesTextView = findViewById(R.id.speciesTextView)
        statusTextView = findViewById(R.id.statusTextView)
        genderTextView = findViewById(R.id.genderTextView)
    }

}