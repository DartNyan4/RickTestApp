package com.example.ricktestapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.ricktestapp.R
import com.example.ricktestapp.fragments.details.DetailsDataItem
import com.example.ricktestapp.fragments.details.DetailsFragmentDirections
import com.example.ricktestapp.viewholders.models.CharacterDataModel
import com.example.ricktestapp.viewmodels.CharactersViewModel

class CharactersFragment : PaginationFragment() {

    override val viewModel: CharactersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.listener = { item ->
            if (item is CharacterDataModel) {
                val action = CharactersFragmentDirections.actionCharactersFragmentToDetailsFragment(
                    item.toDetailsDataItem()
                )
                NavHostFragment.findNavController(this).navigate(action)
            }
        }
    }

    override fun setUpObservers() {
        super.setUpObservers()
        viewModel.charactersLiveData.observe(viewLifecycleOwner, { response ->
            adapter.submitList(response.map { CharacterDataModel(it) })
        })
    }

}