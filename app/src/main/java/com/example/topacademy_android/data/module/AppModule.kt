package com.example.topacademy_android.data.module

import android.content.Context
import android.content.SharedPreferences
import com.example.topacademy_android.data.repository.CarRepositoryImpl
import com.example.topacademy_android.data.repository.ListRepositoryImpl
import com.example.topacademy_android.data.repository.WeatherRepositoryImpl
import com.example.topacademy_android.data.service.RetrofitClient
import com.example.topacademy_android.data.service.WeatherApi
import com.example.topacademy_android.domain.repository.CarRepository
import com.example.topacademy_android.domain.repository.ListRepository
import com.example.topacademy_android.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideCarRepository(): CarRepository {
        return CarRepositoryImpl()
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideListRepository(prefs: SharedPreferences): ListRepository {
        return ListRepositoryImpl(prefs)
    }

    @Provides
    fun provideWeatherRepository(api: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(api)
    }
}