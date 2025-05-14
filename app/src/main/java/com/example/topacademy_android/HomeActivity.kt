package com.example.topacademy_android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<Button>(R.id.btn_calculator).setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
        }

        findViewById<Button>(R.id.btn_list).setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }

        findViewById<Button>(R.id.btn_weather).setOnClickListener {
            startActivity(Intent(this, WeatherActivity::class.java))
        }
    }
}