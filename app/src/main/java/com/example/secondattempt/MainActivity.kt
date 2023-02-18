package com.example.secondattempt

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.secondattempt.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val expressionText: TextView = binding.expression.expressionResultText

        binding.buttons.buttonOne.setOnClickListener{
            val newExpressionText = expressionText.text.toString() + "1"
            expressionText.text = newExpressionText
        }

    }
}