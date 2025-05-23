package com.example.topacademy_android.data.repository

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.topacademy_android.data.service.RetrofitClient
import com.example.topacademy_android.data.service.WeatherApi
import com.example.topacademy_android.domain.model.WeatherResponse
import com.example.topacademy_android.domain.repository.WeatherRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getWeather(lat: Double, lon: Double): WeatherResponse? {
        if (lat !in -90.0..90.0 || lon !in -180.0..180.0) {
            Log.e("Weather", "Invalid coordinates")
            return null
        }
            try {
                val response = api.getWeatherForecast(lon, lat)
                if (response.isSuccessful) {
                    Log.d("Weather", "Data: ${response.body()}")
                    return response.body()
                } else {
                    Log.e("Weather", "Error code: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("Weather", "Network error: ${e.message}")
            }
        return null
    }
}