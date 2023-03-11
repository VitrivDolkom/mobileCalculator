package com.example.calculator

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainActivityViewModel>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val view = binding.root
        
        setOnButtonsClickListeners()
        
        viewModel.currentExpression.observe(this) {
            binding.expression.expressionResultText.text = it.toString()
        }
        
        setContentView(view)
    }
    
    private fun setOnButtonsClickListeners() {
        val buttons = mapOf(
            binding.buttons.buttonZero to Operation.ZERO,
            binding.buttons.buttonOne to Operation.ONE,
            binding.buttons.buttonTwo to Operation.TWO,
            binding.buttons.buttonThree to Operation.THREE,
            binding.buttons.buttonFour to Operation.FOUR,
            binding.buttons.buttonFive to Operation.FIVE,
            binding.buttons.buttonSix to Operation.SIX,
            binding.buttons.buttonSeven to Operation.SEVEN,
            binding.buttons.buttonEight to Operation.EIGHT,
            binding.buttons.buttonNine to Operation.NINE,
            binding.buttons.buttonComma to Operation.COMMA,
            binding.buttons.buttonAc to Operation.RESET,
            binding.buttons.buttonPlus to Operation.PLUS,
            binding.buttons.buttonMinus to Operation.MINUS,
            binding.buttons.buttonPlusMinus to Operation.PLUS_MINUS,
            binding.buttons.buttonDivide to Operation.DIVIDE,
            binding.buttons.buttonPercent to Operation.PERCENT,
            binding.buttons.buttonMultiplication to Operation.MULTIPLICATION,
            binding.buttons.buttonEqual to Operation.EQUAL
        )
        
        buttons.forEach { (button, operation) ->
            button.setOnClickListener {
                viewModel.changeExpression(operation)
            }
        }
    }
}