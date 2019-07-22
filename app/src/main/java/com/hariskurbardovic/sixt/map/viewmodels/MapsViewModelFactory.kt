package com.hariskurbardovic.sixt.map.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hariskurbardovic.sixt.map.repositories.MapsRepository

/**
 * Factory for creating a [MapsViewModel] with a constructor that takes a [MapsRepository].
 */
class MapsViewModelFactory(
    private val mapsRepository: MapsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = MapsViewModel(mapsRepository) as T
}