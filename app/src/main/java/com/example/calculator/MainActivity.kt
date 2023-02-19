package com.example.calculator

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val expressionText: TextView = binding.expression.expressionResultText

        binding.expression.buttonErase.setOnClickListener{this.eraseSymbol(expressionText)}

        binding.buttons.buttonZero.setOnClickListener{this.addToViewText(expressionText, '0')}
        binding.buttons.buttonOne.setOnClickListener{this.addToViewText(expressionText, '1')}
        binding.buttons.buttonTwo.setOnClickListener{this.addToViewText(expressionText, '2')}
        binding.buttons.buttonThree.setOnClickListener{this.addToViewText(expressionText, '3')}
        binding.buttons.buttonFour.setOnClickListener{this.addToViewText(expressionText, '4')}
        binding.buttons.buttonFive.setOnClickListener{this.addToViewText(expressionText, '5')}
        binding.buttons.buttonSix.setOnClickListener{this.addToViewText(expressionText, '6')}
        binding.buttons.buttonSeven.setOnClickListener{this.addToViewText(expressionText, '7')}
        binding.buttons.buttonEight.setOnClickListener{this.addToViewText(expressionText, '8')}
        binding.buttons.buttonNine.setOnClickListener{this.addToViewText(expressionText, '9')}
    }

    private fun eraseSymbol(view: TextView) {
        view.text = view.text.toString().replaceFirst(".$".toRegex(), "")
    }

    private fun addToViewText(view: TextView, symbolToAdd: Char) {
        view.text = view.text.toString() + symbolToAdd
    }
}