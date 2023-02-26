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
        
        setContentView(view)
    }
    
    fun onEraseButtonClick(it: View) {
        val expressionText = binding.expression.expressionResultText.text
        
        binding.expression.expressionResultText.text = expressionText.toString().replaceFirst(".$".toRegex(), "")
    }
    
    fun onCalculatorButtonsClick(it: View) {
        val btn = it as Button
        
        if (btn.text.toString() == "AC") {
            binding.expression.expressionResultText.text = "0"
            return;
        }
        
        val newText = calculateExpression.validatedExpressionWithNewSymbol(
            btn.text.toString(), binding.expression.expressionResultText.text.toString()
        )
        
        if (newText == "Error") {
            binding.expression.expressionResultText.setTextColor(getColor(R.color.text_error_expression))
        } else {
            binding.expression.expressionResultText.setTextColor(getColor(R.color.expression_result_text))
        }
        
        binding.expression.expressionResultText.text = newText
    }
}