package com.hariskurbardovic.sixt.network.responses

import com.hariskurbardovic.sixt.database.models.Car

class CarResponse(
    private val id: String?,
    private val modelIdentifier: String?,
    private val modelName: String?,
    private val name: String?,
    private val make: String?,
    private val group: String?,
    private val color: String?,
    private val series: String?,
    private val fuelType: String?,
    private val fuelLevel: Double?,
    private val transmission: String?,
    private val licensePlate: String?,
    private val latitude: Double?,
    private val longitude: Double?,
    private val innerCleanliness: String?,
    private val carImageUrl: String?
) {

    fun toCarDb() = Car(
        id ?: "",
        modelIdentifier ?: "",
        modelName ?: "",
        name ?: "",
        make ?: "",
        group ?: "",
        color ?: "",
        series ?: "",
        fuelType ?: "",
        fuelLevel ?: 0.0,
        transmission ?: "",
        licensePlate ?: "",
        latitude ?: 0.0,
        longitude ?: 0.0,
        innerCleanliness ?: "",
        carImageUrl ?: ""
    )
}