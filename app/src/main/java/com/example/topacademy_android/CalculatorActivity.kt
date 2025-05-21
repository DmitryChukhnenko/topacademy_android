package com.example.topacademy_android

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.topacademy_android.databinding.ActivityCalculatorBinding
import java.util.Stack

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private var currentInput = StringBuilder()
    private var result = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.calculator_title)

        binding.btn0.setOnClickListener { appendToInput("0") }
        binding.btn1.setOnClickListener { appendToInput("1") }
        binding.btn2.setOnClickListener { appendToInput("2") }
        binding.btn3.setOnClickListener { appendToInput("3") }
        binding.btn4.setOnClickListener { appendToInput("4") }
        binding.btn5.setOnClickListener { appendToInput("5") }
        binding.btn6.setOnClickListener { appendToInput("6") }
        binding.btn7.setOnClickListener { appendToInput("7") }
        binding.btn8.setOnClickListener { appendToInput("8") }
        binding.btn9.setOnClickListener { appendToInput("9") }
        binding.btnDot.setOnClickListener { appendToInput(".") }

        binding.btnAdd.setOnClickListener { appendToInput("+") }
        binding.btnSubtract.setOnClickListener { appendToInput("-") }
        binding.btnMultiply.setOnClickListener { appendToInput("*") }
        binding.btnDivide.setOnClickListener { appendToInput("/") }

        binding.btnClear.setOnClickListener { clearInput() }
        binding.btnDelete.setOnClickListener { deleteLastChar() }
        binding.btnEquals.setOnClickListener { calculateResult() }
    }

    private fun appendToInput(value: String) {
        currentInput.append(value)
        updateDisplay()
    }

    private fun clearInput() {
        currentInput.clear()
        result = 0.0
        updateDisplay()
    }

    private fun deleteLastChar() {
        if (currentInput.isNotEmpty()) {
            currentInput.deleteCharAt(currentInput.length - 1)
            updateDisplay()
        }
    }



    private fun calculateResult() {
        try {
            val result = evaluateExpression(currentInput.toString())
            currentInput.clear().append(result)
            updateDisplay()
        } catch (e: Exception) {
            Toast.makeText(this, "Ошибка вычисления", Toast.LENGTH_SHORT).show()
        }
    }

    private fun evaluateExpression(expression: String): Double {
        val values = Stack<Double>()
        val ops = Stack<Char>()
        val tokens = expression.toCharArray()

        var i = 0
        while (i < tokens.size) {
            when {
                tokens[i] == ' ' -> i++
                tokens[i] in '0'..'9' || tokens[i] == '.' -> {
                    val sbuf = StringBuilder()
                    while (i < tokens.size && (tokens[i] in '0'..'9' || tokens[i] == '.')) {
                        sbuf.append(tokens[i++])
                    }
                    values.push(sbuf.toString().toDouble())
                    i--
                }
                else -> {
                    while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())) {
                        values.push(applyOp(ops.pop(), values.pop(), values.pop()))
                    }
                    ops.push(tokens[i])
                }
            }
            i++
        }

        while (!ops.empty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()))
        }

        return values.pop()
    }

    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        return !((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
    }

    private fun applyOp(op: Char, b: Double, a: Double): Double {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> a / b
            else -> 0.0
        }
    }

    private fun updateDisplay() {
        binding.tvDisplay.text = currentInput.ifEmpty { "0" }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}