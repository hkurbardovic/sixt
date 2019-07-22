package com.hariskurbardovic.sixt.map.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hariskurbardovic.sixt.database.models.Car
import com.hariskurbardovic.sixt.map.MapsActivity
import com.hariskurbardovic.sixt.map.repositories.MapsRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * The ViewModel for [MapsActivity].
 */
class MapsViewModel internal constructor(
    private val mapsRepository: MapsRepository
) : ViewModel() {

    val carsResponse: LiveData<List<Car>> = mapsRepository.carsResponse
    val exception: MutableLiveData<Exception> = mapsRepository.exception

    fun getCars() {
        viewModelScope.launch {
            mapsRepository.getCars()
        }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}