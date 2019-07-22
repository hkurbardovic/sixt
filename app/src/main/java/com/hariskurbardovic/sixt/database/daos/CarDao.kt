package com.hariskurbardovic.sixt.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hariskurbardovic.sixt.database.models.Car

@Dao
interface CarDao {

    @Query("SELECT * FROM cars")
    fun getAll(): LiveData<List<Car>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCar(cars: Car)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cars: List<Car>)
}