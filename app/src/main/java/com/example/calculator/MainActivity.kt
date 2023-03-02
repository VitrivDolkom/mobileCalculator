package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val calculateExpression = CalculateExpression()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        
        binding.expression.buttonErase.setOnClickListener {
            val expressionText = binding.expression.expressionResultText.text
            
            binding.expression.expressionResultText.text = expressionText.toString().replaceFirst(".$".toRegex(), "")
        }
        
        val operationButtonIds = arrayOf(
            binding.buttons.buttonPlus,
            binding.buttons.buttonMinus,
            binding.buttons.buttonPercent,
            binding.buttons.buttonMultiplication,
            binding.buttons.buttonDivide,
            binding.buttons.buttonEqual
        )
        
        
        setContentView(view)
    }
    
    private fun setExpressionColor(color: Int) {
        binding.expression.expressionResultText.setTextColor(color)
    }
    
    fun onCalculatorButtonsClick(it: View) {
        val btn = it as Button
        
        if (btn.text.toString() == "AC") {
            binding.expression.expressionResultText.text = "0"
            setExpressionColor(getColor(R.color.expression_result_text))
            return;
        }
        
        val newText = calculateExpression.validatedExpressionWithNewSymbol(
            btn.text.toString(), binding.expression.expressionResultText.text.toString()
        )
        
        if (newText == "Error") {
            setExpressionColor(getColor(R.color.text_error_expression))
        } else {
            setExpressionColor(getColor(R.color.expression_result_text))
        }
        
        binding.expression.expressionResultText.text = newText
    }
}