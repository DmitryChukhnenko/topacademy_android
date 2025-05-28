package com.example.topacademy_android.presentation.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topacademy_android.domain.usecase.CalculateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val calculateUseCase: CalculateUseCase
) : ViewModel() {
    private val _expression = MutableStateFlow("")
    val expression: StateFlow<String> = _expression

    private val _result = MutableStateFlow("")
    val result: StateFlow<String> = _result

    fun appendToInput(value: String) {
        _expression.value += value
    }

    fun clearInput() {
        _expression.value = ""
        _result.value = ""
    }

    fun deleteLastChar() {
        _expression.value = _expression.value.dropLast(1)
    }

    fun calculate() {
        viewModelScope.launch {
            try {
                val result = calculateUseCase(_expression.value)
                _result.value = result.toString()
                _expression.value = result.toString()
            } catch (e: Exception) {
                _result.value = "Error"
            }
        }
    }
}