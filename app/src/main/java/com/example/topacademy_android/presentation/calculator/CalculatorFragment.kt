package com.example.topacademy_android.presentation.calculator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.FragmentCalculatorBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalculatorFragment : Fragment(R.layout.fragment_calculator) {
    private val viewModel: CalculatorViewModel by viewModel()
    private lateinit var binding: FragmentCalculatorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCalculatorBinding.bind(view)

        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
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
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.expression.collect { expr ->
                    binding.tvDisplay.text = expr.ifEmpty { getString(R.string.calculator_placeholder) }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.result.collect { res ->
                    if (res.isNotEmpty()) {
                        binding.tvDisplay.text = res
                    }
                }
            }
        }
    }
}