package com.hariskurbardovic.sixt.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.hariskurbardovic.sixt.database.SixtDatabase
import com.hariskurbardovic.sixt.map.repositories.MapsRepository
import com.hariskurbardovic.sixt.map.viewmodels.MapsViewModel
import com.hariskurbardovic.sixt.utilities.getValue
import org.hamcrest.Matchers
import org.junit.*

class MapsViewModelTest {

    private lateinit var appDatabase: SixtDatabase
    private lateinit var viewModel: MapsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, SixtDatabase::class.java).build()

        val mapsRepository = MapsRepository()
        viewModel = MapsViewModel(mapsRepository)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun testDefaultValues() {
        Assert.assertThat(getValue(viewModel.carsResponse).size, Matchers.equalTo(28))
    }
}