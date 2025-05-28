package com.example.topacademy_android.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.ActivityHomeBinding
import com.example.topacademy_android.presentation.list.ListActivity
import com.example.topacademy_android.presentation.weather.WeatherActivity
import com.example.topacademy_android.presentation.calculator.CalculatorActivity
import com.example.topacademy_android.presentation.car.CarListActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_home)

        binding.btnCalculator.setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
        }

        binding.btnList.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }

        binding.btnWeather.setOnClickListener {
            startActivity(Intent(this, WeatherActivity::class.java))
        }

        binding.btnCarList.setOnClickListener {
            startActivity(Intent(this, CarListActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}