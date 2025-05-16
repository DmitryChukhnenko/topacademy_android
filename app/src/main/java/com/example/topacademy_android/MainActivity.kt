package com.example.topacademy_android

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.topacademy_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (isValidEmail(email) && isValidPassword(password)) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                showError()
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length > 6
    }

    private fun showError() {
        val errorMessage = buildString {
            if (!isValidEmail(binding.etEmail.text.toString())) append(getString(R.string.email_error))
            if (!isValidPassword(binding.etPassword.text.toString())) append("\n${getString(R.string.password_error)}")
        }
        binding.tvError.text = errorMessage
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
