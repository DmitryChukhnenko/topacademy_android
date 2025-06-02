package com.example.topacademy_android.di

import com.example.topacademy_android.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {
    factory { CalculateUseCase(get()) }
    factory { GetCarsUseCase(get()) }
    factory { GetWeatherUseCase(get()) }
    factory { LoadItemsUseCase(get()) }
    factory { SaveItemsUseCase(get()) }
}