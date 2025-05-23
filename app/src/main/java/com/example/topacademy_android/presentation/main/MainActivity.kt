package com.example.topacademy_android.presentation.main

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.ActivityMainBinding
import com.example.topacademy_android.presentation.home.HomeActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            val isValid = viewModel.validateCredentials(email, password)
            if (isValid) startActivity(Intent(this, HomeActivity::class.java))
            else showError()
        }
    }

    private fun showError() {
        binding.tvError.text = "Некорректный ввод"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("email", binding.etEmail.text.toString())
        outState.putString("password", binding.etPassword.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.etEmail.setText(savedInstanceState.getString("email"))
        binding.etPassword.setText(savedInstanceState.getString("password"))
    }
}
