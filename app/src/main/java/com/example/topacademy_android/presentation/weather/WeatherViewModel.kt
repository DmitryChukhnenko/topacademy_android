package com.example.topacademy_android.presentation.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topacademy_android.domain.model.WeatherResponse
import com.example.topacademy_android.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

    fun loadWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _weatherData.value = getWeatherUseCase(lat, lon)
        }
    }

    fun clearData() {
        _weatherData.value = null
    }
}