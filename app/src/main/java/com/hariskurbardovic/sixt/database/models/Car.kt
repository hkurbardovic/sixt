package com.hariskurbardovic.sixt.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
class Car(
    @PrimaryKey val id: String = "",
    val modelIdentifier: String,
    val modelName: String,
    val name: String,
    val make: String,
    val group: String,
    val color: String,
    val series: String,
    val fuelType: String,
    val fuelLevel: Double,
    val transmission: String,
    val licensePlate: String,
    val latitude: Double,
    val longitude: Double,
    val innerCleanliness: String,
    val carImageUrl: String
) {
    override fun toString() = name
}