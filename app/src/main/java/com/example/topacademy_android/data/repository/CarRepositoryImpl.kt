package com.example.topacademy_android.data.repository

import com.example.topacademy_android.R
import com.example.topacademy_android.domain.model.Car
import com.example.topacademy_android.domain.repository.CarRepository
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor() : CarRepository {
    override fun getCars(): List<Car> {
        return listOf(
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
    }
}