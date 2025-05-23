package com.example.topacademy_android.presentation.car

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.os.Bundle
import androidx.activity.viewModels
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.ActivityCarListBinding

class CarListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarListBinding
    private val viewModel: CarListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.car_list_title)

        binding.rvCars.layoutManager = LinearLayoutManager(this)
        binding.rvCars.adapter = CarAdapter(viewModel.cars.value)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}