package com.example.topacademy_android.domain.usecase

import com.example.topacademy_android.domain.repository.CalculatorRepository

class CalculateUseCase (
    private val repository: CalculatorRepository
) {
    operator fun invoke(expression: String): Double =
        repository.evaluateExpression(expression)
}
