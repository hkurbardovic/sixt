package com.hariskurbardovic.sixt.map.modules

import com.hariskurbardovic.sixt.map.repositories.MapsRepository
import com.hariskurbardovic.sixt.map.viewmodels.MapsViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapsModule {

    @Provides
    @Singleton
    fun providesMapsViewModelFactory(mapsRepository: MapsRepository): MapsViewModelFactory {
        return MapsViewModelFactory(mapsRepository)
    }

    @Provides
    @Singleton
    fun mapRepository(): MapsRepository = MapsRepository()
}