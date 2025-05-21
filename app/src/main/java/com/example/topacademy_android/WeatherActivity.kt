package com.example.topacademy_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topacademy_android.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding
    private val weatherList = listOf(
        Weather("Сегодня", "+25°C", "Солнечно", R.drawable.ic_sun),
        Weather("Завтра", "+22°C", "Облачно", R.drawable.ic_cloud),
        Weather("Послезавтра", "+18°C", "Дождь", R.drawable.ic_rain)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.weather_title)

        binding.rvWeather.layoutManager = LinearLayoutManager(this)
        binding.rvWeather.adapter = WeatherAdapter(weatherList)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}