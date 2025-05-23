package com.example.topacademy_android.presentation.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.topacademy_android.domain.usecase.CalculateUseCase

class CalculatorViewModelFactory(
    private val calculateUseCase: CalculateUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CalculatorViewModel(calculateUseCase) as T
    }
}