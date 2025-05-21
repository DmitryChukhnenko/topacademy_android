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

        // Моковые данные
        val cars = listOf(
            Car(
                brand = "Toyota",
                model = "Camry",
                year = 2022,
                description = "Седан бизнес-класса",
                cost = 30000,
                imageResId = R.drawable.ic_car
            ),
            // Добавьте другие автомобили
        )

        // Настройка Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.car_list_title)

        // Настройка RecyclerView
        binding.rvCars.layoutManager = LinearLayoutManager(this)
        binding.rvCars.adapter = CarAdapter(cars)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}