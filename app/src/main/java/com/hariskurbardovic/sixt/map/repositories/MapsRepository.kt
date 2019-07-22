package com.hariskurbardovic.sixt.map.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hariskurbardovic.sixt.app.SixtApp
import com.hariskurbardovic.sixt.database.SixtDatabase
import com.hariskurbardovic.sixt.database.models.Car
import com.hariskurbardovic.sixt.network.SixtApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MapsRepository {

    @Inject
    lateinit var sixtApiService: SixtApiService

    @Inject
    lateinit var sixtDatabase: SixtDatabase

    init {
        SixtApp.instance.getAppComponent().inject(this)
    }

    val carsResponse: LiveData<List<Car>> = sixtDatabase.carDao().getAll()

    val exception = MutableLiveData<Exception>()

    suspend fun getCars() {
        withContext(Dispatchers.IO) {
            try {
                val cars = sixtApiService.getCars()
                //do something with result
                cars?.let {
                    for (car in cars) {
                        car?.let {
                            sixtDatabase.carDao().insertCar(car.toCarDb())
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    exception.value = e
                }
            }
        }
    }
}