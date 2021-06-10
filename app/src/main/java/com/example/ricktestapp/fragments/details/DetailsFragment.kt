package com.example.ricktestapp.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.example.ricktestapp.R
import com.example.ricktestapp.databinding.FragmentDetailsBinding
import com.example.ricktestapp.utils.BindingFragment
import com.example.ricktestapp.utils.loadUrl
import com.example.ricktestapp.utils.setDefaultLayoutParams
import com.google.android.material.textview.MaterialTextView

class DetailsFragment : BindingFragment<FragmentDetailsBinding>() {

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        args.detailsArg.run {
            imageUrl?.let {
                binding.imageCardView.isVisible = true
                binding.imageView.loadUrl(it)
            }
            binding.titleTextView.text = title
            details.forEach {
                val textView = MaterialTextView(requireContext())
                textView.setDefaultLayoutParams()
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
                textView.text = it
                binding.detailsLayout.addView(textView)
            }
        }
    }

}