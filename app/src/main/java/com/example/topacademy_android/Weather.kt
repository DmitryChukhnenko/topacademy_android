package com.example.topacademy_android

data class WeatherResponse(
    val product: String,
    val init: String,
    val dataseries: List<DataSeries>
)

data class DataSeries(
    val date: Long,
    val weather: String,
    val temp2m: Temp,
    val wind10m_max: Int
)

data class Temp(
    val max: Int,
    val min: Int
)