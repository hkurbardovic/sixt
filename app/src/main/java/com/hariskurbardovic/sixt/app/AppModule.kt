package com.hariskurbardovic.sixt.app

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }
}