package com.hariskurbardovic.sixt.app

import com.hariskurbardovic.sixt.database.SixtDatabase
import com.hariskurbardovic.sixt.database.modules.DatabaseModule
import com.hariskurbardovic.sixt.map.repositories.MapsRepository
import com.hariskurbardovic.sixt.network.SixtApiService
import com.hariskurbardovic.sixt.network.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    fun sixtApiService(): SixtApiService

    fun sixtDatabase(): SixtDatabase

    fun inject(repository: MapsRepository)
}