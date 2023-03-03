package com.example.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val calculateExpression = CalculateExpression()
    private lateinit var viewModel: MainActivityViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        changeExpression()
        
        setContentView(view)
    }
    
    private fun changeExpression() {
        viewModel.currentExpression.observe(this, Observer {
            binding.expression.expressionResultText.text = it.toString()
            
            if (it.toString() == "Error") {
                binding.expression.expressionResultText.setTextColor(getColor(R.color.text_error_expression))
            } else {
                binding.expression.expressionResultText.setTextColor(getColor(R.color.expression_result_text))
            }
        })
        
        val numberButtonIds = arrayOf(
            binding.buttons.buttonZero,
            binding.buttons.buttonOne,
            binding.buttons.buttonTwo,
            binding.buttons.buttonThree,
            binding.buttons.buttonFour,
            binding.buttons.buttonFive,
            binding.buttons.buttonSix,
            binding.buttons.buttonSeven,
            binding.buttons.buttonEight,
            binding.buttons.buttonNine,
            binding.buttons.buttonComma
        )
        
        for (button in numberButtonIds) {
            button.setOnClickListener {
                viewModel.expression += button.text
                viewModel.currentExpression.value = viewModel.expression
            }
        }
        
        val operationButtonIds = arrayOf(
            binding.buttons.buttonPlus,
            binding.buttons.buttonMinus,
            binding.buttons.buttonPercent,
            binding.buttons.buttonMultiplication,
            binding.buttons.buttonDivide,
            binding.buttons.buttonEqual
        )
        
        for (button in operationButtonIds) {
            button.setOnClickListener {
                viewModel.expression = calculateExpression.validatedExpressionWithNewSymbol(
                    button.text.toString(), binding.expression.expressionResultText.text.toString()
                )
                
                viewModel.currentExpression.value = viewModel.expression
            }
        }
        
        binding.expression.buttonErase.setOnClickListener {
            viewModel.expression = viewModel.expression.replaceFirst(".$".toRegex(), "")
            viewModel.currentExpression.value = viewModel.expression
        }
        
        binding.buttons.buttonAc.setOnClickListener {
            viewModel.expression = "0"
            viewModel.currentExpression.value = viewModel.expression
        }
    }
}