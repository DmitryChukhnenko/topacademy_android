package com.example.topacademy_android.di

import android.content.Context
import android.content.SharedPreferences
import com.example.topacademy_android.data.service.RetrofitClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
    }
    single { RetrofitClient.weatherApi }
}