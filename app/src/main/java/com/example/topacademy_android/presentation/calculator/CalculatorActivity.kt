package com.example.topacademy_android.presentation.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.ActivityCalculatorBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupClickListeners()
        observeViewModel()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.calculator_title)
    }

    private fun setupClickListeners() {
        binding.btn0.setOnClickListener { viewModel.appendToInput("0") }
        binding.btn0.setOnClickListener { viewModel.appendToInput("0") }
        binding.btn1.setOnClickListener { viewModel.appendToInput("1") }
        binding.btn2.setOnClickListener { viewModel.appendToInput("2") }
        binding.btn3.setOnClickListener { viewModel.appendToInput("3") }
        binding.btn4.setOnClickListener { viewModel.appendToInput("4") }
        binding.btn5.setOnClickListener { viewModel.appendToInput("5") }
        binding.btn6.setOnClickListener { viewModel.appendToInput("6") }
        binding.btn7.setOnClickListener { viewModel.appendToInput("7") }
        binding.btn8.setOnClickListener { viewModel.appendToInput("8") }
        binding.btn9.setOnClickListener { viewModel.appendToInput("9") }
        binding.btnDot.setOnClickListener { viewModel.appendToInput(".") }

        binding.btnAdd.setOnClickListener { viewModel.appendToInput("+") }
        binding.btnSubtract.setOnClickListener { viewModel.appendToInput("-") }
        binding.btnMultiply.setOnClickListener { viewModel.appendToInput("*") }
        binding.btnDivide.setOnClickListener { viewModel.appendToInput("/") }

        binding.btnClear.setOnClickListener { viewModel.clearInput() }
        binding.btnDelete.setOnClickListener { viewModel.deleteLastChar() }
        binding.btnEquals.setOnClickListener { viewModel.calculate() }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.expression.collect { expr ->
                    binding.tvDisplay.text = expr.ifEmpty { getString(R.string.placeholder) }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.result.collect { res ->
                    if (res.isNotEmpty()) {
                        binding.tvDisplay.text = res
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}