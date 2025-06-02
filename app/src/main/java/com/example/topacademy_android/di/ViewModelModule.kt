package com.example.topacademy_android.di

import com.example.topacademy_android.presentation.calculator.CalculatorViewModel
import com.example.topacademy_android.presentation.car.CarListViewModel
import com.example.topacademy_android.presentation.list.ListViewModel
import com.example.topacademy_android.presentation.main.MainViewModel
import com.example.topacademy_android.presentation.weather.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CalculatorViewModel(get()) }
    viewModel { ListViewModel(get(), get()) }
    viewModel { WeatherViewModel(get()) }
    viewModel { CarListViewModel(get()) }
    viewModel { MainViewModel() }
}