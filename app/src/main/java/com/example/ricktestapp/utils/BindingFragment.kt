package com.example.ricktestapp.utils

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * This class should be inherited by any fragment is using ViewBinding.
 */
abstract class BindingFragment<T : ViewBinding>: Fragment() {

    val binding get() = _binding!!

    // This property is only valid between onCreateView and
    // onDestroyView.
    var _binding: T? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}