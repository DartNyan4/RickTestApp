package com.example.ricktestapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.ricktestapp.R
import com.example.ricktestapp.utils.addDividerItemDecorator
import com.example.ricktestapp.viewholders.models.EpisodeDataModel
import com.example.ricktestapp.viewmodels.EpisodesViewModel

class EpisodesFragment  : PaginationFragment() {

    override val viewModel: EpisodesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.listener = { item ->
            if (item is EpisodeDataModel) {
                val action = EpisodesFragmentDirections.actionEpisodesFragmentToDetailsFragment(
                    item.toDetailsDataItem()
                )
                NavHostFragment.findNavController(this).navigate(action)
            }
        }
    }

    override fun setUpObservers() {
        super.setUpObservers()
        viewModel.episodesLiveData.observe(viewLifecycleOwner, { response ->
            adapter.submitList(response.map { EpisodeDataModel(it) })
        })
    }

    override fun setUpViews() {
        super.setUpViews()
        binding.recyclerView.addDividerItemDecorator(R.color.darkGreen)
    }

}