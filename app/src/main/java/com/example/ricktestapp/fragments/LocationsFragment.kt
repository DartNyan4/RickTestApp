package com.example.ricktestapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.ricktestapp.R
import com.example.ricktestapp.utils.addDividerItemDecorator
import com.example.ricktestapp.viewholders.models.LocationDataModel
import com.example.ricktestapp.viewmodels.LocationViewModel

class LocationsFragment : PaginationFragment() {

    override val viewModel: LocationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.listener = { item ->
            if (item is LocationDataModel) {
                val action = LocationsFragmentDirections.actionLocationsFragmentToDetailsFragment(
                    item.toDetailsDataItem()
                )
                NavHostFragment.findNavController(this).navigate(action)
            }
        }
    }

    override fun setUpObservers() {
        super.setUpObservers()
        viewModel.locationsLiveData.observe(viewLifecycleOwner, { response ->
            adapter.submitList(response.map { LocationDataModel(it) })
        })
    }

    override fun setUpViews() {
        super.setUpViews()
        binding.recyclerView.addDividerItemDecorator(R.color.darkGreen)
    }

}