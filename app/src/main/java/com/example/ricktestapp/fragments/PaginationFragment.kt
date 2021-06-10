package com.example.ricktestapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ricktestapp.adapters.MainAdapter
import com.example.ricktestapp.databinding.FragmentPaginationBinding
import com.example.ricktestapp.utils.BindingFragment
import com.example.ricktestapp.viewmodels.PagerViewModel

abstract class PaginationFragment : BindingFragment<FragmentPaginationBinding>() {

    protected abstract val viewModel: PagerViewModel

    protected val adapter = MainAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaginationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpObservers()
    }

    protected open fun setUpViews() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val manager = recyclerView.layoutManager as? LinearLayoutManager
                val totalItemCount = manager?.itemCount ?: 0
                val lastVisibleItem = manager?.findLastVisibleItemPosition() ?: 0
                if (viewModel.isLoading.not() && totalItemCount <= (lastVisibleItem + 1)) {
                    viewModel.onNextPage()
                }
            }

        })
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onReload()
        }
    }

    protected open fun setUpObservers() {
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner, { isLoading ->
            binding.swipeRefreshLayout.isRefreshing = isLoading
        })
    }


}