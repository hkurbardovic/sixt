package com.hariskurbardovic.sixt.map.components

import com.hariskurbardovic.sixt.map.fragments.ListFragment
import com.hariskurbardovic.sixt.map.fragments.MapFragment
import com.hariskurbardovic.sixt.map.modules.MapsModule
import com.hariskurbardovic.sixt.map.viewmodels.MapsViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MapsModule::class])
interface MapsComponent {

    fun loginViewModelFactory(): MapsViewModelFactory

    fun inject(fragment: MapFragment)

    fun inject(fragment: ListFragment)
}