package com.example.topacademy_android.presentation.weather

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topacademy_android.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.topacademy_android.databinding.ActivityWeatherBinding
import kotlinx.coroutines.launch

class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding
    private val viewModel: WeatherViewModel by viewModel()

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
        supportActionBar?.title = getString(R.string.title_weather)
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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.weatherData.collect { response ->
                    response?.dataseries?.let { data ->
                        binding.rvWeather.adapter = WeatherAdapter(data) { item ->
                            Toast.makeText(
                                this@WeatherActivity,
                                "Погода: ${item.weather}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}