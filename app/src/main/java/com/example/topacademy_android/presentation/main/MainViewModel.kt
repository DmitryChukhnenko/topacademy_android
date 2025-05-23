package com.example.topacademy_android.presentation.main

import android.util.Patterns
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    fun validateCredentials(email: String, password: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6
    }
}