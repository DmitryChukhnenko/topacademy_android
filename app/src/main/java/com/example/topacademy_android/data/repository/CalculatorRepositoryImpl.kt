package com.example.topacademy_android.data.repository

import com.example.topacademy_android.domain.repository.CalculatorRepository
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorRepositoryImpl : CalculatorRepository {
    override fun evaluateExpression(expression: String): Double {
        return ExpressionBuilder(expression).build().evaluate()
    }
}