package com.hariskurbardovic.sixt.map.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.hariskurbardovic.sixt.databinding.FragmentListBinding
import com.hariskurbardovic.sixt.map.adapters.ListAdapter
import com.hariskurbardovic.sixt.map.components.DaggerMapsComponent
import com.hariskurbardovic.sixt.map.modules.MapsModule
import com.hariskurbardovic.sixt.map.viewmodels.MapsViewModel
import com.hariskurbardovic.sixt.map.viewmodels.MapsViewModelFactory
import javax.inject.Inject

class ListFragment : BaseFragment() {

    @Inject
    lateinit var mapsViewModelFactory: MapsViewModelFactory

    private val mapsViewModel: MapsViewModel by viewModels {
        mapsViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(inflater, container, false)

        context ?: return binding.root

        DaggerMapsComponent.builder().mapsModule(MapsModule()).build().inject(this)

        val adapter = ListAdapter()
        binding.recyclerView.adapter = adapter

        observeData(adapter)

        mapsViewModel.getCars()

        return binding.root
    }

    private fun observeData(adapter: ListAdapter) {
        mapsViewModel.carsResponse.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        mapsViewModel.exception.observe(viewLifecycleOwner) {
            handleNetworkError(it)
        }
    }
}