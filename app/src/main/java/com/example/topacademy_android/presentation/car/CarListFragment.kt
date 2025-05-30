package com.example.topacademy_android.presentation.car

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.topacademy_android.databinding.FragmentCarListBinding
import com.example.topacademy_android.R

class CarListFragment : Fragment(R.layout.fragment_car_list) {
    private val viewModel: CarListViewModel by viewModel()
    private lateinit var binding: FragmentCarListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCarListBinding.bind(view)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvCars.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCars.adapter = CarAdapter(viewModel.cars.value)
    }
}