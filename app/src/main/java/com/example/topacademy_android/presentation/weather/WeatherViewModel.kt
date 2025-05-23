package com.example.topacademy_android.presentation.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topacademy_android.data.service.RetrofitClient
import com.example.topacademy_android.domain.model.WeatherResponse
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> = _weatherData

    fun loadWeather(lat: Double, lon: Double) {
        if (lat !in -90.0..90.0 || lon !in -180.0..180.0) {
            Log.e("Weather", "Invalid coordinates")
            return
        }
        viewModelScope.launch {
            try {
                val response = RetrofitClient.weatherApi.getWeatherForecast(lon, lat)
                if (response.isSuccessful) {
                    Log.d("Weather", "Data: ${response.body()}")
                    _weatherData.postValue(response.body())
                } else {
                    Log.e("Weather", "Error code: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("Weather", "Network error: ${e.message}")
            }
        }
    }
}