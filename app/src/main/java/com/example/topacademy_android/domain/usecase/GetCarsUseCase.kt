package com.example.topacademy_android.domain.usecase

import com.example.topacademy_android.domain.model.Car
import com.example.topacademy_android.domain.repository.CarRepository

class GetCarsUseCase (
    private val repository: CarRepository
) {
    operator fun invoke(): List<Car> = repository.getCars()
}