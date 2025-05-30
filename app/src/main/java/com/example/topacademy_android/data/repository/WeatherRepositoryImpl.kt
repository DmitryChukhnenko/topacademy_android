package com.example.topacademy_android.data.repository

import com.example.topacademy_android.data.service.WeatherApi
import com.example.topacademy_android.domain.model.WeatherResponse
import com.example.topacademy_android.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getWeather(lat: Double, lon: Double): WeatherResponse? {
        if (lat !in -90.0..90.0 || lon !in -180.0..180.0) {
            return null
        }
        return try {
            api.getWeatherForecast(lon, lat).takeIf { it.isSuccessful }?.body()
        } catch (e: Exception) {
            null
        }
    }
}