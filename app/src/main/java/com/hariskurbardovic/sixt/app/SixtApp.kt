package com.hariskurbardovic.sixt.app

import android.app.Application
import com.hariskurbardovic.sixt.database.modules.DatabaseModule
import com.hariskurbardovic.sixt.network.modules.NetworkModule
import com.hariskurbardovic.sixt.network.modules.OkHttpClientModule

class SixtApp : Application() {

    companion object {
        lateinit var instance: SixtApp
    }

    private lateinit var appComponent: AppComponent

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .databaseModule(DatabaseModule())
            .networkModule(NetworkModule())
            .okHttpClientModule(OkHttpClientModule())
            .build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}