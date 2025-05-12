package com.example.topacademy_android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.topacademy_android.databinding.ActivityMainBinding

// Логи:
//2025-05-12 21:19:02.870  5577-5577  MAIN_ACTIVITY           com.example.topacademy_android       I  Activity created!: 54 ms
//2025-05-12 21:19:02.876  5577-5577  MAIN_ACTIVITY           com.example.topacademy_android       I  Activity started!: 6 ms
//2025-05-12 21:19:02.877  5577-5577  MAIN_ACTIVITY           com.example.topacademy_android       I  Activity resumed!: 1 ms

class MainActivity : AppCompatActivity() {

    companion object {
        private const val MAIN_ACTIVITY = "MAIN_ACTIVITY"
    }

    private lateinit var binding: ActivityMainBinding
    private var previousTimeControl: Long = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        log(getString(R.string.on_create))
    }

    private fun log(input: String) {
        Log.i(MAIN_ACTIVITY, "${input}: ${System.currentTimeMillis() - previousTimeControl} ms")
        previousTimeControl = System.currentTimeMillis()
    }

    override fun onStart() {
        log(getString(R.string.on_start))
        super.onStart()
    }

    override fun onResume() {
        log(getString(R.string.on_resume))
        super.onResume()
    }

    override fun onPause() {
        log(getString(R.string.on_pause))
        super.onPause()
    }

    override fun onStop() {
        log(getString(R.string.on_stop))
        super.onStop()
    }

    override fun onRestart() {
        log(getString(R.string.on_restart))
        super.onRestart()
    }

    override fun onDestroy() {
        log(getString(R.string.on_destroy))
        super.onDestroy()
    }
}
