package com.example.topacademy_android.domain.repository

interface CalculatorRepository {
    fun evaluateExpression(expression: String): Double
}