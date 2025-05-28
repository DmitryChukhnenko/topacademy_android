package com.example.topacademy_android.domain.usecase

import com.example.topacademy_android.domain.model.WeatherResponse
import com.example.topacademy_android.domain.repository.WeatherRepository

class GetWeatherUseCase (
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double): WeatherResponse? =
        repository.getWeather(lat, lon)
}