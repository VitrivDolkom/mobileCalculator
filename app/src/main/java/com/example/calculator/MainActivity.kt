package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val ce = CalculateExpression()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root


        setContentView(view)
    }

    fun eraseSymbol(it: View) {
        var expressionText = binding.expression.expressionResultText.text

        binding.expression.expressionResultText.text = expressionText.toString().replaceFirst(".$".toRegex(), "")
    }

    fun onButtonsClick(it: View) {
        val btn = it as Button

        val newText =  binding.expression.expressionResultText.text.toString() + btn.text
        binding.expression.expressionResultText.text = newText
    }

}