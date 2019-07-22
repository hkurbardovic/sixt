package com.hariskurbardovic.sixt.network.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hariskurbardovic.sixt.network.SixtApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [OkHttpClientModule::class])
class NetworkModule {

    companion object {
        const val BASE_URL = "https://cdn.sixt.io/"
    }

    @Provides
    @Singleton
    fun sixtApiService(retrofit: Retrofit): SixtApiService {
        return retrofit.create(SixtApiService::class.java)
    }

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun gsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }
}