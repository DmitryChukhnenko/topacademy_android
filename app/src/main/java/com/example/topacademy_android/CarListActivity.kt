package com.example.topacademy_android

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.os.Bundle
import com.example.topacademy_android.databinding.ActivityCarListBinding

class CarListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cars = listOf(
            Car(
                brand = "Toyota",
                model = "Camry",
                year = 2022,
                description = "Седан бизнес-класса",
                cost = 30000,
                imageResId = R.drawable.ic_car
            ),
            Car(
                brand = "Honda",
                model = "Civic",
                year = 2023,
                description = "Компактный седан с гибридным двигателем",
                cost = 25000,
                imageResId = R.drawable.ic_car
            ),
            Car(
                brand = "Ford",
                model = "Mustang",
                year = 2021,
                description = "Легендарный спортивный автомобиль",
                cost = 45000,
                imageResId = R.drawable.ic_car
            ),
            Car(
                brand = "Tesla",
                model = "Model S",
                year = 2023,
                description = "Электрический седан с автопилотом",
                cost = 80000,
                imageResId = R.drawable.ic_car
            ),
            Car(
                brand = "BMW",
                model = "X5",
                year = 2022,
                description = "Премиальный кроссовер",
                cost = 70000,
                imageResId = R.drawable.ic_car
            )
        )

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.car_list_title)

        binding.rvCars.layoutManager = LinearLayoutManager(this)
        binding.rvCars.adapter = CarAdapter(cars)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}