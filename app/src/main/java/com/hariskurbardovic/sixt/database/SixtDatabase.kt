package com.hariskurbardovic.sixt.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hariskurbardovic.sixt.database.daos.CarDao
import com.hariskurbardovic.sixt.database.models.Car

/**
 * The Room database for this app
 */
@Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class SixtDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object {

        private const val DB_NAME = "sixt"
        // For Singleton instantiation
        @Volatile
        private var instance: SixtDatabase? = null

        fun getInstance(context: Context): SixtDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): SixtDatabase {
            return Room.databaseBuilder(
                context, SixtDatabase::class.java,
                DB_NAME
            ).build()
        }
    }
}