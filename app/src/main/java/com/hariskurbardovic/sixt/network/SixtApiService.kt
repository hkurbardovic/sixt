package com.hariskurbardovic.sixt.network

import com.hariskurbardovic.sixt.network.responses.CarResponse
import retrofit2.http.GET

interface SixtApiService {

    @GET("codingtask/cars")
    suspend fun getCars(): List<CarResponse?>?
}