package com.example.topacademy_android.presentation.car

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topacademy_android.domain.model.Car
import com.example.topacademy_android.domain.usecase.GetCarsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarListViewModel(
    private val getCarsUseCase: GetCarsUseCase
) : ViewModel() {
    private val _cars = MutableStateFlow<List<Car>>(emptyList())
    val cars: StateFlow<List<Car>> = _cars

    init {
        loadCars()
    }

    private fun loadCars() {
        viewModelScope.launch {
            _cars.value = getCarsUseCase()
        }
    }
}