package com.example.topacademy_android.domain.repository

import com.example.topacademy_android.domain.model.Car

interface CarRepository {
    fun getCars(): List<Car>
}