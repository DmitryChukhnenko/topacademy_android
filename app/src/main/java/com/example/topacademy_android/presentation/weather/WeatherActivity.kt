package com.example.topacademy_android.presentation.weather

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupUI()
        setupObservers()
        loadInitialData()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.weather_title)
    }

    private fun setupUI() {
        binding.rvWeather.layoutManager = LinearLayoutManager(this)
        binding.rvWeather.adapter = WeatherAdapter(emptyList()) { /*...*/ }

        binding.btnUpdate.setOnClickListener {
            val lat = binding.etLatitude.text.toString().toDoubleOrNull()
            val lon = binding.etLongitude.text.toString().toDoubleOrNull()

            if (lat != null && lon != null) {
                viewModel.loadWeather(lat, lon)
            } else {
                Toast.makeText(this, "Некорректные координаты", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadInitialData() {
        binding.etLatitude.setText("55.7558")
        binding.etLongitude.setText("37.6176")
    }

    private fun setupObservers() {
        viewModel.weatherData.observe(this) { response ->
            response?.dataseries?.let { data ->
                binding.rvWeather.adapter = WeatherAdapter(data) { item ->
                    Toast.makeText(this, "Погода: ${item.weather}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}