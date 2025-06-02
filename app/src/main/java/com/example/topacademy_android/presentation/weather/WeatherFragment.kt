package com.example.topacademy_android.presentation.weather

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topacademy_android.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.topacademy_android.databinding.FragmentWeatherBinding
import kotlinx.coroutines.launch

class WeatherFragment : Fragment(R.layout.fragment_weather) {
    private val lat by lazy { arguments?.getFloat("lat") ?: 55.7558f }
    private val lon by lazy { arguments?.getFloat("lon") ?: 37.6176f }

    private val viewModel: WeatherViewModel by viewModel()
    private lateinit var binding: FragmentWeatherBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWeatherBinding.bind(view)

        setupUI()
        loadInitialData()
        setupObservers()
    }

    private fun loadInitialData() {
        binding.etLatitude.setText(lat.toString())
        binding.etLongitude.setText(lon.toString())
    }

    private fun setupUI() {
        binding.rvWeather.layoutManager = LinearLayoutManager(requireContext())
        binding.rvWeather.adapter = WeatherAdapter(emptyList()) { item ->
            Toast.makeText(
                requireContext(),
                "Погода: ${item.weather}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.btnUpdate.setOnClickListener {
            val lat = binding.etLatitude.text.toString().toDoubleOrNull()
            val lon = binding.etLongitude.text.toString().toDoubleOrNull()

            if (lat != null && lon != null) {
                viewModel.loadWeather(lat, lon)
            } else {
                Toast.makeText(requireContext(), "Некорректные координаты", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.weatherData.collect { response ->
                    response?.dataseries?.let { data ->
                        binding.rvWeather.adapter = WeatherAdapter(data) { item ->
                            Toast.makeText(
                                requireContext(),
                                "Погода: ${item.weather}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}