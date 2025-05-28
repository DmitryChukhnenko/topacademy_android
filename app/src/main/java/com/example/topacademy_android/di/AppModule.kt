package com.example.topacademy_android.di

import android.content.Context
import android.content.SharedPreferences
import com.example.topacademy_android.data.repository.*
import com.example.topacademy_android.data.service.RetrofitClient
import com.example.topacademy_android.domain.repository.*
import com.example.topacademy_android.domain.usecase.CalculateUseCase
import com.example.topacademy_android.presentation.calculator.CalculatorViewModel
import com.example.topacademy_android.presentation.car.CarListViewModel
import com.example.topacademy_android.presentation.list.ListViewModel
import com.example.topacademy_android.presentation.weather.WeatherViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<CarRepository> { CarRepositoryImpl() }
    single<CalculatorRepository> { CalculatorRepositoryImpl() }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single { RetrofitClient.weatherApi }

    single<SharedPreferences> {
        androidContext().getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
    }
    single<ListRepository> { ListRepositoryImpl(get()) }

    factory { CalculateUseCase(get()) }

    viewModel { CarListViewModel(get()) }
    viewModel { ListViewModel(get()) }
    viewModel { WeatherViewModel(get()) }
    viewModel { CalculatorViewModel(get()) }
}