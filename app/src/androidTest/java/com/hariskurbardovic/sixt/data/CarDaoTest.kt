package com.hariskurbardovic.sixt.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.hariskurbardovic.sixt.database.SixtDatabase
import com.hariskurbardovic.sixt.database.daos.CarDao
import com.hariskurbardovic.sixt.database.models.Car
import com.hariskurbardovic.sixt.utilities.getValue
import org.hamcrest.Matchers
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CarDaoTest {

    private lateinit var database: SixtDatabase
    private lateinit var carDao: CarDao

    private val carA = Car(
        "1", "", "", "CarA", "", "",
        "", "", "", 0.0, "", "",
        0.0, 0.0, "", ""
    )
    private val carB = Car(
        "2", "", "", "CarB", "", "",
        "", "", "", 0.0, "", "",
        0.0, 0.0, "", ""
    )
    private val carC = Car(
        "3", "", "", "CarC", "", "",
        "", "", "", 0.0, "", "",
        0.0, 0.0, "", ""
    )
    private val carASameIdDifferentName = Car(
        "1", "", "", "CarA", "", "",
        "", "", "", 0.0, "", "",
        0.0, 0.0, "", ""
    )

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SixtDatabase::class.java).build()
        carDao = database.carDao()

        carDao.insertAll(listOf(carA, carB, carC, carASameIdDifferentName))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetCarsMatchesId() {
        val carList = getValue(carDao.getAll())
        Assert.assertThat(carList.size, Matchers.equalTo(3))

        Assert.assertThat(carList[0].id, Matchers.equalTo(carB.id))
        Assert.assertThat(carList[1].id, Matchers.equalTo(carC.id))
        Assert.assertThat(carList[2].id, Matchers.equalTo(carASameIdDifferentName.id))
    }

    @Test
    fun testGetCarsMatchesName() {
        val carList = getValue(carDao.getAll())
        Assert.assertThat(carList.size, Matchers.equalTo(3))

        Assert.assertThat(carList[0].name, Matchers.equalTo(carB.name))
        Assert.assertThat(carList[1].name, Matchers.equalTo(carC.name))
        Assert.assertThat(carList[2].name, Matchers.equalTo(carASameIdDifferentName.name))
    }
}