package com.example.topacademy_android.presentation.home

import android.os.Bundle
import android.view.View
import androidx.core.bundle.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.btnCalculator.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_calculatorFragment)
        }

        binding.btnList.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_listFragment)
        }

        binding.btnWeather.setOnClickListener {
            // Используем bundle вместо Directions
            val bundle = bundleOf(
                "lat" to 55.7558f,
                "lon" to 37.6176f
            )
            findNavController().navigate(R.id.action_homeFragment_to_weatherFragment, bundle)
        }

        binding.btnCarList.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_carListFragment)
        }
    }
}