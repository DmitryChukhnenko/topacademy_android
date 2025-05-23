package com.example.topacademy_android.domain.repository

import com.example.topacademy_android.domain.model.WeatherResponse

interface WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double): WeatherResponse?
}