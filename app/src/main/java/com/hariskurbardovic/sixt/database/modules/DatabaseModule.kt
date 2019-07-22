package com.hariskurbardovic.sixt.database.modules

import android.content.Context
import com.hariskurbardovic.sixt.app.AppModule
import com.hariskurbardovic.sixt.database.SixtDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class DatabaseModule {

    @Provides
    @Singleton
    fun sixtDatabase(context: Context): SixtDatabase {
        return SixtDatabase.getInstance(context)
    }
}