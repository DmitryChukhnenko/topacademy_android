package com.example.topacademy_android.domain.usecase

import com.example.topacademy_android.domain.repository.CalculatorRepository

class CalculateUseCase(private val repository: CalculatorRepository) {
    fun execute(expression: String): Double {
        return repository.evaluateExpression(expression)
    }
}