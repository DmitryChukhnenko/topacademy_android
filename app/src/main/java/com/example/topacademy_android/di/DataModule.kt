package com.example.topacademy_android.di

import com.example.topacademy_android.data.repository.*
import com.example.topacademy_android.domain.repository.CalculatorRepository
import com.example.topacademy_android.domain.repository.CarRepository
import com.example.topacademy_android.domain.repository.ListRepository
import com.example.topacademy_android.domain.repository.WeatherRepository
import org.koin.dsl.module

val dataModule = module {
    single<CarRepository> { CarRepositoryImpl() }
    single<CalculatorRepository> { CalculatorRepositoryImpl() }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<ListRepository> { ListRepositoryImpl(get()) }
}